package com.aliano.service;

import com.aliano.entity.ProductInfo;
import com.aliano.vo.ProductExcelVO;
import com.aliano.vo.SellerProductInfoVO;
import com.aliano.vo.SellerProductInfoVO2;
import com.aliano.vo.SellerProductInfoVO3;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
public interface ProductInfoService extends IService<ProductInfo> {
    public BigDecimal findPriceById(Integer id);
    public Boolean subStockById(Integer id,Integer quantity);
    public Boolean addStock(Integer id,Integer quantity);
    public SellerProductInfoVO2 sellerProductInfoVO(Integer page, Integer size);
    public SellerProductInfoVO2 sellerProductInfoVOLike(String keyWord, Integer page, Integer size);
    public SellerProductInfoVO2 sellerProductInfoVOByCategoryType(Integer category, Integer page, Integer size);
    public SellerProductInfoVO3 sellerProductInfoVOById(Integer id);
    public List<ProductExcelVO> excelVOList();
    public List<ProductInfo> excleToProductInfoList(InputStream inputStream);
}
