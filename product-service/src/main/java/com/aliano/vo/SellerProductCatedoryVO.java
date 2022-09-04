package com.aliano.vo;

import com.aliano.entity.ProductCategory;
import lombok.Data;

import java.util.List;

/**
 * @Author Cure
 * @Time 2022/5/22 21:29
 */
@Data
public class SellerProductCatedoryVO {
    private String name;
    private Integer type;
    private List goods;
}
