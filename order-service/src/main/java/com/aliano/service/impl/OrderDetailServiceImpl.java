package com.aliano.service.impl;

import com.aliano.entity.OrderDetail;
import com.aliano.mapper.OrderDetailMapper;
import com.aliano.service.OrderDetailService;
import com.aliano.util.EChartsColorUtil;
import com.aliano.vo.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Autowired(required = false)
    private OrderDetailMapper orderDetailMapper;

    @Override
    public BarVO barData() {
        List<BarResultVO> barResultVOS = this.orderDetailMapper.barData();
        List<String> names = new ArrayList<>();
        List<BarValueVO> values = new ArrayList<>();
        for (BarResultVO barResultVO : barResultVOS) {
            names.add(barResultVO.getName());
            BarValueVO barValueVO = new BarValueVO();
            barValueVO.setValue(barResultVO.getValue());
            barValueVO.setItemStyle(EChartsColorUtil.createItemStyle(barResultVO.getValue()));
            values.add(barValueVO);
        }
        BarVO barVO = new BarVO();
        barVO.setNames(names);
        barVO.setValues(values);
        return barVO;
    }

    @Override
    public BasicLineVO basicLineData() {
        List<BasicLineResultVO> basicLineResultVOS = this.orderDetailMapper.basicLineData();
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (BasicLineResultVO basicLineResultVO : basicLineResultVOS) {
            names.add(basicLineResultVO.getDate());
            values.add(basicLineResultVO.getValue());
        }
        BasicLineVO basicLineVO = new BasicLineVO();
        basicLineVO.setNames(names);
        basicLineVO.setValues(values);
        return basicLineVO;
    }

    @Override
    public StackedLineVO stackedLineData() {
        List<String> names = this.orderDetailMapper.names();
        List<String> dates = this.orderDetailMapper.dates();
        List<StackedLineInnerVO> datas = new ArrayList<>();
        for (String name : names) {
            List<Integer> data = this.orderDetailMapper.stackedData(name);
            StackedLineInnerVO vo = new StackedLineInnerVO();
            vo.setName(name);
            vo.setData(data);
            datas.add(vo);
        }
        StackedLineVO stackedLineVO = new StackedLineVO();
        stackedLineVO.setNames(names);
        stackedLineVO.setDatas(datas);
        stackedLineVO.setDates(dates);
        return stackedLineVO;
    }
}
