package com.aliano.mapper;

import com.aliano.entity.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    public BigDecimal findPriceById(Integer id);
    public Integer findStockById(Integer id);
    public int updateStock(Integer id,Integer stock);
    public int addStock(Integer id,Integer quantity);

}
