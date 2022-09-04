package com.aliano.controller;


import com.aliano.entity.ProductInfo;
import com.aliano.service.ProductCategoryService;
import com.aliano.service.ProductInfoService;
import com.aliano.util.ResultVOUtil;
import com.aliano.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    //商品列表
    @GetMapping("/list")
    public ResultVO list(){
        //ResultVO resultVO = new ResultVO();
        //resultVO.setCode(0);
        //resultVO.setMsg("成功");
        //resultVO.setData(this.productCategoryService.buyerlist());
        return ResultVOUtil.success(this.productCategoryService.buyerlist());
    }

    //根据 ID 查询商品价格
    @GetMapping("/findPriceById/{id}")
    public BigDecimal findPriceById(@PathVariable("id") Integer id){
        //ProductInfo productInfo = this.productInfoService.getById(id);
        //return productInfo.getProductPrice();
        return this.productInfoService.findPriceById(id);
    }

    //通过 ID 查询商品
    @GetMapping("/findById/{id}")
    public ProductInfo findById(@PathVariable("id") Integer id){
        return this.productInfoService.getById(id);
    }

    //减库存
    @PutMapping("/subStockById/{id}/{quantity}")
    public Boolean subStockById(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity){
       return this.productInfoService.subStockById(id, quantity);
    }

    //加库存
    @PutMapping("/addStockById/{id}/{quantity}")
    public Boolean addStockById(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity){
        return this.productInfoService.addStock(id, quantity);
    }

}

