package com.aliano.vo;

import lombok.Data;

import java.util.Map;

/**
 * @Author Cure
 * @Time 2022/5/23 20:52
 */
@Data
public class BarValueVO {
    private Integer value;
    private Map<String, String> itemStyle;
}
