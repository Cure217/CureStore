package com.aliano.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Cure
 * @Time 2022/5/23 14:20
 */
@Data
public class SellerProductInfoForm {
    private Integer categoryType;
    private String productDescription;
    private String productIcon;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStatus;
    private Integer productStock;
}
