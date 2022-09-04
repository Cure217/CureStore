package com.aliano.vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author Cure
 * @Time 2022/5/23 20:51
 */
@Data
public class BarVO {
    private List<String> names;
    private List<BarValueVO> values;
}
