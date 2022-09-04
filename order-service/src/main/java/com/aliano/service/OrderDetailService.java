package com.aliano.service;

import com.aliano.entity.OrderDetail;
import com.aliano.vo.BarVO;
import com.aliano.vo.BasicLineVO;
import com.aliano.vo.StackedLineVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
public interface OrderDetailService extends IService<OrderDetail> {
    public BarVO barData();
    public BasicLineVO basicLineData();
    public StackedLineVO stackedLineData();
}
