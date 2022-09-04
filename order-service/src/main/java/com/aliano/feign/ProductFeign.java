package com.aliano.feign;

import com.aliano.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.math.BigDecimal;

/**
 * @Author Cure
 * @Time 2022/5/19 21:10
 */
@FeignClient("product-service")
public interface ProductFeign {
    @GetMapping("/buyer/product/findPriceById/{id}")
    public BigDecimal findPriceById(@PathVariable("id") Integer id);
    @GetMapping("/buyer/product/findById/{id}")
    public ProductInfo findById(@PathVariable("id") Integer id);
    @PutMapping("/buyer/product/subStockById/{id}/{quantity}")
    public Boolean subStockById(@PathVariable("id") Integer id, @PathVariable("quantity") Integer quantity);
    @PutMapping("/buyer/product/addStockById/{id}/{quantity}")
    public Boolean addStockById(@PathVariable("id") Integer id, @PathVariable("quantity") Integer quantity);
}
