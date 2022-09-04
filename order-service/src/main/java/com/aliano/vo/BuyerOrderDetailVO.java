package com.aliano.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Cure
 * @Time 2022/5/21 21:13
 */
@Data
public class BuyerOrderDetailVO {
    private String detailId;
    private String orderId;
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;

}
