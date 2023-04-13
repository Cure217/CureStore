package com.aliano.service.impl;

import com.aliano.entity.ProductCategory;
import com.aliano.entity.ProductInfo;
import com.aliano.mapper.ProductCategoryMapper;
import com.aliano.mapper.ProductInfoMapper;
import com.aliano.service.ProductCategoryService;
import com.aliano.vo.BuyerProductCategoryVO;
import com.aliano.vo.BuyerProductInfoVO;
import com.aliano.vo.SellerProductCatedoryVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired(required = false)
    private ProductCategoryMapper productCategoryMapper;

    @Autowired(required = false)
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<BuyerProductCategoryVO> buyerlist() {
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(null);
        List<BuyerProductCategoryVO> result = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            BuyerProductCategoryVO vo = new BuyerProductCategoryVO();
            vo.setName(productCategory.getCategoryName());
            vo.setType(productCategory.getCategoryType());
            QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type",productCategory.getCategoryType());
            List<ProductInfo> productInfoList = this.productInfoMapper.selectList(queryWrapper);
            List<BuyerProductInfoVO> buyerProductInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                BuyerProductInfoVO vo1 = new BuyerProductInfoVO();
                //vo1.setProductId(productInfo.getProductId());
                BeanUtils.copyProperties(productInfo,vo1); // 工具类 用来快速执行上面的set get操作
                buyerProductInfoVOS.add(vo1);
            }
            vo.setGoods(buyerProductInfoVOS);
            result.add(vo);
        }
        return result;
    }

    @Override
    public List<SellerProductCatedoryVO> sellerlist() {
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(null);
        List<SellerProductCatedoryVO> sellerProductCatedoryVOS = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            SellerProductCatedoryVO vo = new SellerProductCatedoryVO();
            vo.setName(productCategory.getCategoryName());
            vo.setType(productCategory.getCategoryType());
            sellerProductCatedoryVOS.add(vo);
        }
        return sellerProductCatedoryVOS;
    }
}
