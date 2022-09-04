package com.aliano.vo;

import lombok.Data;

import java.util.List;

@Data
public class BasicLineVO {
    private List<String> names;
    private List<Integer> values;
}
