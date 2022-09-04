package com.aliano.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author Cure
 * @Time 2022/5/24 15:44
 */
@Data
public class StackedLineInnerVO {
    private String name;
    private String type = "line";
    private String stack = "销量";
    private List<Integer> data;
}
