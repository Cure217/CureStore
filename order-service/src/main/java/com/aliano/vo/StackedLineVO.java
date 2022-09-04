package com.aliano.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author Cure
 * @Time 2022/5/24 15:43
 */
@Data
public class StackedLineVO {
    private List<String> names;
    private List<String> dates;
    private List<StackedLineInnerVO> datas;
}
