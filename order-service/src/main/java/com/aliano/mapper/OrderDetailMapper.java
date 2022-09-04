package com.aliano.mapper;

import com.aliano.entity.OrderDetail;
import com.aliano.vo.BarResultVO;
import com.aliano.vo.BasicLineResultVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    public List<BarResultVO> barData();
    public List<BasicLineResultVO> basicLineData();
    public List<String> names();
    public List<String> dates();
    public List<Integer> stackedData(String name);
}
