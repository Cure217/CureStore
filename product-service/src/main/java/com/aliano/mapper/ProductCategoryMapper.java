package com.aliano.mapper;

import com.aliano.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    public String findNameByType(Integer type);
}
