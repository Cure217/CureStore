package com.aliano.controller;


import com.aliano.entity.OrderMaster;
import com.aliano.service.OrderDetailService;
import com.aliano.service.OrderMasterService;
import com.aliano.util.ResultVOUtil;
import com.aliano.vo.BarVO;
import com.aliano.vo.ResultVO;
import com.aliano.vo.SellerOrderMasterVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Arrays;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private OrderDetailService orderDetailService;

    //查询订单
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        Page<OrderMaster> orderMasterPage = new Page<>(page, size);
        Page<OrderMaster> resultPage = this.orderMasterService.page(orderMasterPage);
        SellerOrderMasterVO vo = new SellerOrderMasterVO();
        vo.setTotal(resultPage.getTotal());
        vo.setSize(resultPage.getSize());
        vo.setContent(resultPage.getRecords());
        return ResultVOUtil.success(vo);
    }

    //取消订单
    @PutMapping("/cancel/{orderId}")
    public ResultVO cancel(@PathVariable("orderId") String orderId){
        this.orderMasterService.cancel(null, orderId);
        return ResultVOUtil.success(null);
    }

    //完结订单
    @PutMapping("/finish/{orderId}")
    public ResultVO finish(@PathVariable("orderId") String orderId){
        this.orderMasterService.finish(orderId);
        return ResultVOUtil.success(null);
    }

    //柱状图 只统计商品的总销量
    @GetMapping("barSale")
    public ResultVO barSale() {
        BarVO barVO = this.orderDetailService.barData();
        return ResultVOUtil.success(barVO);
    }

    //基础折线图 只统计每一天的销量
    @GetMapping("/basicLineSale")
    public ResultVO basicLineSale(){
        return ResultVOUtil.success(this.orderDetailService.basicLineData());
    }

    //折线图堆叠 统计某个商品某几天的销量 某一天各自的销量
    @GetMapping("/stackedLineSale")
    public ResultVO stackedLineSale(){
        return ResultVOUtil.success(this.orderDetailService.stackedLineData());
    }
}

