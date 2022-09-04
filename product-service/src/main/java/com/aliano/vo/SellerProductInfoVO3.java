package com.aliano.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class SellerProductInfoVO3 {
    private Integer id;
    private BigDecimal price;
    private Boolean status;
    private String name;
    private Integer stock;
    private String description;
    private String icon;
    private Map<String,Integer> category;
}
