package com.aliano.mapper;

import com.aliano.entity.OrderMaster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
public interface OrderMasterMapper extends BaseMapper<OrderMaster> {
    public boolean cancel(Integer buyerId,String orderId);
    public boolean cancel2(String orderId);
    public boolean finish(String orderId);
    public boolean pay(Integer buyerId,String orderId);
}
