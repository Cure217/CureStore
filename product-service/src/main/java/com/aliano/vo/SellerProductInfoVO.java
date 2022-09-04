package com.aliano.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Cure
 * @Time 2022/5/23 15:05
 */

@Data
public class SellerProductInfoVO {
    private Integer id;
    private Boolean status;
    private BigDecimal price;
    private String name;
    private Integer stock;
    private String description;
    private String icon;
    private String categoryName;
}