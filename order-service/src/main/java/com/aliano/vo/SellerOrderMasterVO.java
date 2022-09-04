package com.aliano.vo;

import com.aliano.entity.OrderMaster;
import lombok.Data;

import java.util.List;

/**
 * @Author Cure
 * @Time 2022/5/23 19:56
 */
@Data
public class SellerOrderMasterVO {
    private List<OrderMaster> content;
    private Long size;
    private Long total;
}
