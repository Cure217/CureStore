package com.aliano.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Cure
 * @Time 2022/5/21 21:16
 */
@Data
public class BuyerOrderMasterVO {private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<BuyerOrderDetailVO> orderDetailList;
}
