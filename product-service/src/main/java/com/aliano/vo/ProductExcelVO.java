package com.aliano.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductExcelVO {
    @ExcelProperty("商品编号")
    private Integer productId;
    @ExcelProperty("商品名称")
    private String productName;
    @ExcelProperty("商品单价")
    private BigDecimal productPrice;
    @ExcelProperty("商品库存")
    private Integer productStock;
    @ExcelProperty("商品描述")
    private String productDescription;
    @ExcelProperty("商品图标")
    private String productIcon;
    @ExcelProperty("商品状态")
    private String productStatus;
    @ExcelProperty("商品分类")
    private Integer categoryType;
    @ExcelProperty("分类名称")
    private String categoryName;
}