package com.aliano.vo;

import com.aliano.entity.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author Cure
 * @Time 2022/5/19 15:53
 *
 * vo view Object 视图对象
 *
 */
@Data
public class BuyerProductCategoryVO {
    private String name;
    private Integer type;
    private List<BuyerProductInfoVO> goods;
}
