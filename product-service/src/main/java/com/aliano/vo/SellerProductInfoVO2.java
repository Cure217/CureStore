package com.aliano.vo;

import lombok.Data;

import java.util.List;

@Data
public class SellerProductInfoVO2 {
    private List<SellerProductInfoVO> content;
    private Long size;
    private Long total;
}
