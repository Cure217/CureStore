package com.aliano.service;

import com.aliano.entity.ProductCategory;
import com.aliano.vo.BuyerProductCategoryVO;
import com.aliano.vo.SellerProductCatedoryVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<BuyerProductCategoryVO> buyerlist();
    public List<SellerProductCatedoryVO> sellerlist();

}
