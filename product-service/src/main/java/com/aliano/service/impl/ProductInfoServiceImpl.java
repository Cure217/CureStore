package com.aliano.service.impl;

import com.aliano.entity.ProductCategory;
import com.aliano.entity.ProductInfo;
import com.aliano.exception.ShopException;
import com.aliano.mapper.ProductCategoryMapper;
import com.aliano.mapper.ProductInfoMapper;
import com.aliano.result.ResponseEnum;
import com.aliano.service.ProductInfoService;
import com.aliano.vo.ProductExcelVO;
import com.aliano.vo.SellerProductInfoVO;
import com.aliano.vo.SellerProductInfoVO2;
import com.aliano.vo.SellerProductInfoVO3;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
@Service
@Slf4j
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

    @Autowired(required = false)
    private ProductInfoMapper productInfoMapper;

    @Autowired(required = false)
    private ProductCategoryMapper productCategoryMapper;

    // 重入锁 或者在Boolean前面加synchronized
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public BigDecimal findPriceById(Integer id) {
        return this.productInfoMapper.findPriceById(id);
    }

    @Override
    public Boolean subStockById(Integer id, Integer quantity) {
        //判断库存是否正确
        Integer stock = this.productInfoMapper.findStockById(id);
        if ((stock < quantity)) {
            //抛异常
            throw new ShopException(ResponseEnum.STOCK_ERROR.getMsg());
        }
        reentrantLock.lock();
        Integer result = stock - quantity;
        this.productInfoMapper.updateStock(id, result);
        reentrantLock.unlock();
        return true;
    }

    @Override
    public Boolean addStock(Integer id, Integer quantity) {
        this.productInfoMapper.addStock(id, quantity);
        return true;
    }

    @Override
    public SellerProductInfoVO2 sellerProductInfoVO(Integer page, Integer size) {
        Page<ProductInfo> infoPage = new Page<>(page, size);
        Page<ProductInfo> productInfoPage = this.productInfoMapper.selectPage(infoPage, null);
        SellerProductInfoVO2 vo2 = new SellerProductInfoVO2();
        vo2.setSize(productInfoPage.getSize());
        vo2.setTotal(productInfoPage.getTotal());
        List<ProductInfo> records = productInfoPage.getRecords();
        List<SellerProductInfoVO> voList = new ArrayList<>();
        for (ProductInfo record : records) {
            SellerProductInfoVO vo1 = new SellerProductInfoVO();
            vo1.setId(record.getProductId());
            vo1.setPrice(record.getProductPrice());
            vo1.setStock(record.getProductStock());
            if (record.getProductStatus() == 1) {
                vo1.setStatus(true);
            } else {
                vo1.setStatus(false);
            }
            vo1.setName(record.getProductName());
            vo1.setIcon(record.getProductIcon());
            vo1.setDescription(record.getProductDescription());
            QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type", record.getCategoryType());
            ProductCategory productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            vo1.setCategoryName(productCategory.getCategoryName());
            voList.add(vo1);
        }
        vo2.setContent(voList);
        return vo2;
    }

    @Override
    public SellerProductInfoVO2 sellerProductInfoVOLike(String keyWord, Integer page, Integer size) {
        Page<ProductInfo> infoPage = new Page<>(page, size);
        QueryWrapper<ProductInfo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.like("product_name", keyWord);
        Page<ProductInfo> productInfoPage = this.productInfoMapper.selectPage(infoPage, queryWrapper1);
        SellerProductInfoVO2 vo2 = new SellerProductInfoVO2();
        vo2.setSize(productInfoPage.getSize());
        vo2.setTotal(productInfoPage.getTotal());
        List<ProductInfo> records = productInfoPage.getRecords();
        List<SellerProductInfoVO> voList = new ArrayList<>();
        for (ProductInfo record : records) {
            SellerProductInfoVO vo1 = new SellerProductInfoVO();
            vo1.setId(record.getProductId());
            vo1.setPrice(record.getProductPrice());
            vo1.setStock(record.getProductStock());
            if (record.getProductStatus() == 1) {
                vo1.setStatus(true);
            } else {
                vo1.setStatus(false);
            }
            vo1.setName(record.getProductName());
            vo1.setIcon(record.getProductIcon());
            vo1.setDescription(record.getProductDescription());
            QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type", record.getCategoryType());
            ProductCategory productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            vo1.setCategoryName(productCategory.getCategoryName());
            voList.add(vo1);

        }
        vo2.setContent(voList);
        return vo2;
    }

    @Override
    public SellerProductInfoVO2 sellerProductInfoVOByCategoryType(Integer category, Integer page, Integer size) {
        Page<ProductInfo> infoPage = new Page<>(page, size);
        QueryWrapper<ProductInfo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("category_type", category);
        Page<ProductInfo> productInfoPage = this.productInfoMapper.selectPage(infoPage, queryWrapper1);
        SellerProductInfoVO2 vo2 = new SellerProductInfoVO2();
        vo2.setSize(productInfoPage.getSize());
        vo2.setTotal(productInfoPage.getTotal());
        List<ProductInfo> records = productInfoPage.getRecords();
        List<SellerProductInfoVO> voList = new ArrayList<>();
        for (ProductInfo record : records) {
            SellerProductInfoVO vo1 = new SellerProductInfoVO();
            vo1.setId(record.getProductId());
            vo1.setPrice(record.getProductPrice());
            vo1.setStock(record.getProductStock());
            if (record.getProductStatus() == 1) {
                vo1.setStatus(true);
            } else {
                vo1.setStatus(false);
            }
            vo1.setName(record.getProductName());
            vo1.setIcon(record.getProductIcon());
            vo1.setDescription(record.getProductDescription());
            QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_type", record.getCategoryType());
            ProductCategory productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            vo1.setCategoryName(productCategory.getCategoryName());
            voList.add(vo1);
        }
        vo2.setContent(voList);
        return vo2;
    }

    @Override
    public SellerProductInfoVO3 sellerProductInfoVOById(Integer id) {
        ProductInfo productInfo = this.productInfoMapper.selectById(id);
        SellerProductInfoVO3 vo1 = new SellerProductInfoVO3();
        vo1.setId(productInfo.getProductId());
        vo1.setPrice(productInfo.getProductPrice());
        vo1.setStock(productInfo.getProductStock());
        if (productInfo.getProductStatus() == 1) {
            vo1.setStatus(true);
        } else {
            vo1.setStatus(false);
        }
        vo1.setName(productInfo.getProductName());
        vo1.setIcon(productInfo.getProductIcon());
        vo1.setDescription(productInfo.getProductDescription());
        Map<String, Integer> map = new HashMap<>();
        map.put("categoryType",productInfo.getCategoryType());
        vo1.setCategory(map);
       return vo1;
    }

    @Override
    public List<ProductExcelVO> excelVOList() {
        List<ProductInfo> productInfoList = this.productInfoMapper.selectList(null);
        List<ProductExcelVO> productExcelVOList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            ProductExcelVO vo = new ProductExcelVO();
            BeanUtils.copyProperties(productInfo, vo);
            if (productInfo.getProductStatus() == 1) {
                vo.setProductStatus("上架");
            }else {
                vo.setProductStatus("下架");
            }
            vo.setCategoryName(productCategoryMapper.findNameByType(productInfo.getCategoryType()));
            productExcelVOList.add(vo);
        }

        return productExcelVOList;
    }

    @Override
    public List<ProductInfo> excleToProductInfoList(InputStream inputStream) {
        try {
            List<ProductInfo> list = new ArrayList<>();
            EasyExcel.read(inputStream)
                    .head(ProductExcelVO.class)
                    .sheet()
                    .registerReadListener(new AnalysisEventListener<ProductExcelVO>() {

                        @Override
                        public void invoke(ProductExcelVO excelData, AnalysisContext analysisContext) {
                            ProductInfo productInfo = new ProductInfo();
                            BeanUtils.copyProperties(excelData, productInfo);
                            if(excelData.getProductStatus().equals("上架")){
                                productInfo.setProductStatus(1);
                            }else{
                                productInfo.setProductStatus(0);
                            }
                            list.add(productInfo);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                            log.info("=========================文件解析完成=========================");
                        }
                    }).doRead();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

