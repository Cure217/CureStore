package com.aliano.controller;


import com.aliano.entity.ProductInfo;
import com.aliano.form.SellerProductInfoForm;
import com.aliano.form.SellerProductInfoUpdateForm;
import com.aliano.handler.CustomCellWriteHandler;
import com.aliano.service.ProductCategoryService;
import com.aliano.service.ProductInfoService;
import com.aliano.util.ResultVOUtil;
import com.aliano.vo.ProductExcelVO;
import com.aliano.vo.ResultVO;
import com.aliano.vo.SellerProductInfoVO2;
import com.aliano.vo.SellerProductInfoVO3;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author aliano
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    //查询所有商品分类
    @GetMapping("/findAllProductCategory")
    public ResultVO findAllProductCategory(){
        Map<String, List> map = new HashMap<>();
        map.put("content",productCategoryService.sellerlist());
        return ResultVOUtil.success(map);
    }

    //添加商品
    @PostMapping("/add")
    public ResultVO add(@RequestBody SellerProductInfoForm form){
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(form,productInfo);
        this.productInfoService.save(productInfo);
        return ResultVOUtil.success(null);
    }

    //查询商品
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable Integer page,
                         @PathVariable Integer size){
        SellerProductInfoVO2 vo2 = this.productInfoService.sellerProductInfoVO(page, size);
        return ResultVOUtil.success(vo2);

    }

    //商品模糊查询
    @GetMapping("/like/{keyWord}/{page}/{size}")
    public ResultVO like(
            @PathVariable String keyWord,
            @PathVariable Integer page,
            @PathVariable Integer size){
        SellerProductInfoVO2 vo2 = this.productInfoService.sellerProductInfoVOLike(keyWord,page, size);
        return ResultVOUtil.success(vo2);

    }

    //通过分类查询商品
    @GetMapping("/findByCategory/{categoryType}/{page}/{size}")
    public ResultVO findByCategory(@PathVariable Integer categoryType,
                                   @PathVariable Integer page,
                                   @PathVariable Integer size){
        SellerProductInfoVO2 vo2 = this.productInfoService.sellerProductInfoVOByCategoryType(categoryType,page, size);
        return ResultVOUtil.success(vo2);
    }

    //通过 ID 查询商品
    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable Integer id){
        SellerProductInfoVO3 vo3 = this.productInfoService.sellerProductInfoVOById(id);
        return ResultVOUtil.success(vo3);
    }

    //通过 ID 删除商品
    @DeleteMapping("/delete/{id}")
    public ResultVO delete(@PathVariable Integer id){
        this.productInfoService.removeById(id);
        return ResultVOUtil.success(null);
    }

    //修改商品状态
    @PutMapping("/updateStatus/{id}/{status}")
    public ResultVO updateStatus(
            @PathVariable Integer id,
            @PathVariable Boolean status){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(id);
        if (status) {
            productInfo.setProductStatus(1);
        }else {
            productInfo.setProductStatus(0);
        }
        this.productInfoService.updateById(productInfo);
        return ResultVOUtil.success(null);
    }

    //修改商品
    @PutMapping("update")
    public ResultVO update(@RequestBody SellerProductInfoUpdateForm form){
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(form,productInfo);
        if (form.getStatus()) {
            productInfo.setProductStatus(1);
        }else {
            productInfo.setProductStatus(0);
        }
        productInfo.setCategoryType(form.getCategory().getCategoryType());
        this.productInfoService.updateById(productInfo);
        return ResultVOUtil.success(null);
    }

    //导出Excel
    @GetMapping("/export")
    public void exportData(HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("商品信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //获取ProductExcelVO类型的List
            List<ProductExcelVO> productExcelVOList = this.productInfoService.excelVOList();
            EasyExcel.write(response.getOutputStream(), ProductExcelVO.class)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .sheet("商品信息")
                    .doWrite(productExcelVOList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //导入Excel
    @PostMapping("/import")
    public ResultVO importData(@RequestParam("file") MultipartFile file){
        List<ProductInfo> productInfoList = null;
        try {
            productInfoList = this.productInfoService.excleToProductInfoList(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(productInfoList==null){
            return ResultVOUtil.fail("导入Excel失败！");
        }
        boolean result = this.productInfoService.saveBatch(productInfoList);
        if(result)return ResultVOUtil.success(null);
        return ResultVOUtil.fail("导入Excel失败！");
    }
}

