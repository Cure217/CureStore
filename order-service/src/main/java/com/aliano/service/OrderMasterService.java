package com.aliano.service;

import com.aliano.entity.OrderMaster;
import com.aliano.form.BuyerOrderForm;
import com.aliano.vo.BarVO;
import com.aliano.vo.BuyerOrderMasterVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
public interface OrderMasterService extends IService<OrderMaster> {
    public String create(BuyerOrderForm buyerOrderForm);
    public BuyerOrderMasterVO detail(Integer buyerId,String orderId);
    public boolean cancel(Integer buyerId,String orderId);
    public boolean finish(String orderId);
    public boolean pay(Integer buyerId,String orderId);

}
