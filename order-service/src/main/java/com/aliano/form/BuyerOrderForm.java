package com.aliano.form;

import lombok.Data;

import java.util.List;

/**
 * @Author Cure
 * @Time 2022/5/19 20:44
 * 作为参数列表
 */
@Data
public class BuyerOrderForm {
    private String name;
    private String phone;
    private String address;
    private Integer id;
    private List<BuyerOrderDetailForm> items;
}
