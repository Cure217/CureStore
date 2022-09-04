package com.aliano.service.impl;

import com.aliano.entity.OrderDetail;
import com.aliano.entity.OrderMaster;
import com.aliano.entity.ProductInfo;
import com.aliano.feign.ProductFeign;
import com.aliano.form.BuyerOrderDetailForm;
import com.aliano.form.BuyerOrderForm;
import com.aliano.mapper.OrderDetailMapper;
import com.aliano.mapper.OrderMasterMapper;
import com.aliano.service.OrderMasterService;
import com.aliano.vo.BuyerOrderDetailVO;
import com.aliano.vo.BuyerOrderMasterVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements OrderMasterService {

    @Autowired
    private ProductFeign productFeign;

    @Autowired(required = false)
    private OrderMasterMapper orderMasterMapper;

    @Autowired(required = false)
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    @Transactional
    public String create(BuyerOrderForm buyerOrderForm) {
        //先存主表
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName(buyerOrderForm.getName());
        orderMaster.setBuyerAddress(buyerOrderForm.getAddress());
        orderMaster.setBuyerPhone(buyerOrderForm.getPhone());
        orderMaster.setBuyerOpenid(buyerOrderForm.getId());
        List<BuyerOrderDetailForm> items = buyerOrderForm.getItems();
        BigDecimal orderAmount = new BigDecimal(0);
        for (BuyerOrderDetailForm item : items) {
            Integer productId = item.getProductId();
            Integer productQuantity = item.getProductQuantity();
            //通过id查商品价格
            BigDecimal price = this.productFeign.findPriceById(productId);
            BigDecimal amount = price.multiply(new BigDecimal(productQuantity));
            orderAmount = orderAmount.add(amount);
        }
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setPayStatus(0);
        orderMaster.setOrderStatus(0);
        this.orderMasterMapper.insert(orderMaster);
        //再存从表
        for (BuyerOrderDetailForm item : items) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderMaster.getOrderId());
            orderDetail.setProductId(item.getProductId());
            orderDetail.setProductQuantity(item.getProductQuantity());
            ProductInfo productInfo = this.productFeign.findById(item.getProductId());
            BeanUtils.copyProperties(productInfo,orderDetail);
            this.orderDetailMapper.insert(orderDetail);
            //减库存
            this.productFeign.subStockById(item.getProductId(),item.getProductQuantity());
        }
        //通知后台管理系统
        //this.webSocket.sendMessage("有新的订单");
        return orderMaster.getOrderId();
    }

    @Override
    public BuyerOrderMasterVO detail(Integer buyerId, String orderId) {
        QueryWrapper<OrderMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer_openid",buyerId);
        queryWrapper.eq("order_id",orderId);
        OrderMaster orderMaster = this.orderMasterMapper.selectOne(queryWrapper);
        BuyerOrderMasterVO orderMasterVO = new BuyerOrderMasterVO();
        BeanUtils.copyProperties(orderMaster,orderMasterVO);
        QueryWrapper<OrderDetail> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("order_id",orderId);
        List<OrderDetail> orderDetailList = this.orderDetailMapper.selectList(queryWrapper1);
        List<BuyerOrderDetailVO> buyerOrderDetailVOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            BuyerOrderDetailVO detailVO = new BuyerOrderDetailVO();
            BeanUtils.copyProperties(orderDetail,detailVO);
            buyerOrderDetailVOList.add(detailVO);
        }
        orderMasterVO.setOrderDetailList(buyerOrderDetailVOList);
        return orderMasterVO;

    }

    @Override
    @Transactional
    public boolean cancel(Integer buyerId, String orderId) {
        //加库存
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id ",orderId);
        List<OrderDetail> orderDetailList = this.orderDetailMapper.selectList(queryWrapper);
        for (OrderDetail orderDetail : orderDetailList) {
            this.productFeign.addStockById(orderDetail.getProductId(),orderDetail.getProductQuantity());
        }
        if (buyerId == null){
            return  this.orderMasterMapper.cancel2(orderId);
        }else {
            return this.orderMasterMapper.cancel(buyerId,orderId);
        }

    }

    @Override
    public boolean finish(String orderId) {
        return this.orderMasterMapper.finish(orderId);
    }

    @Override
    @Transactional
    public boolean pay(Integer buyerId, String orderId) {
        //通知后台管理系统
        //this.webSocket.sendMessage("有新的订单");
        //将消息存入 MQ
        this.rocketMQTemplate.convertAndSend("myTopic","有新的订单");
        return this.orderMasterMapper.pay(buyerId,orderId);
    }

}
