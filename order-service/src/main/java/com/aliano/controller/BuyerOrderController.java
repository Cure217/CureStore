package com.aliano.controller;


import com.aliano.entity.OrderMaster;
import com.aliano.form.BuyerOrderForm;
import com.aliano.service.OrderMasterService;
import com.aliano.util.ResultVOUtil;
import com.aliano.vo.BuyerOrderMasterVO;
import com.aliano.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.beans.binding.IntegerBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单详情表 前端控制器
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    //创建订单
    @PostMapping("/create")
    public ResultVO create(@RequestBody BuyerOrderForm buyerOrderForm){
        String orderId = this.orderMasterService.create(buyerOrderForm);
        //ResultVO resultVO = new ResultVO();
        //resultVO.setCode(0);
        //resultVO.setMsg("成功");
        Map<String, String> map = new HashMap<>();
        map.put("orderId",orderId);
        //resultVO.setData(map);
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list/{buyerId}/{page}/{size}")
    public ResultVO list(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
            ){
        Page<OrderMaster> orderMasterPage = new Page<>(page,size);
        QueryWrapper<OrderMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer_openid",buyerId);
        Page<OrderMaster> resultpage = this.orderMasterService.page(orderMasterPage,queryWrapper);
        List<OrderMaster> records = resultpage.getRecords();
        return ResultVOUtil.success(records);

    }

    //查询订单详情
    @GetMapping("/detail/{buyerId}/{orderId}")
    public ResultVO detail(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("orderId") String orderId
            ){
        BuyerOrderMasterVO detail = this.orderMasterService.detail(buyerId, orderId);
        return ResultVOUtil.success(detail);
    }

    //取消订单
    @PutMapping("/cancel/{buyerId}/{orderId}")
    public ResultVO cancel(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("orderId") String orderId
    ){
        this.orderMasterService.cancel(buyerId, orderId);
        return ResultVOUtil.success(null);
    }

    //完结订单
    @PutMapping("/finish/{orderId}")
    public ResultVO finish(
            @PathVariable("orderId") String orderId
    ){
        this.orderMasterService.finish(orderId);
        return ResultVOUtil.success(null);
    }

    //支付订单
    @PutMapping("/pay/{buyerId}/{orderId}")
    public ResultVO pay(
            @PathVariable("buyerId") Integer buyerId,
            @PathVariable("orderId") String orderId
    ){
        this.orderMasterService.pay(buyerId, orderId);
        return ResultVOUtil.success(null);
    }
}

