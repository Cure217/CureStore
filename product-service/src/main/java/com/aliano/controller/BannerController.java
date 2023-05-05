package com.aliano.controller;


import com.aliano.entity.Banner;
import com.aliano.entity.ProductInfo;
import com.aliano.form.SellerProductInfoForm;
import com.aliano.mapper.BannerMapper;
import com.aliano.service.BannerService;
import com.aliano.util.ResultVOUtil;
import com.aliano.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aliano
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    public BannerMapper bannerMapper;

    @Autowired
    public BannerService bannerService;

    //轮播图
    @GetMapping("/bannerlist")
    public ResultVO list(){
        return ResultVOUtil.success(this.bannerService.list());
    }

    //添加轮播图
    @PostMapping ("/addbanner")
    public ResultVO addbanner(@RequestBody Banner banner){
        this.bannerService.addBanner(banner);
        return ResultVOUtil.success(null);
    }

    //修改轮播图状态
    @PutMapping("/updateStatus/{id}/{bannerStatus}")
    public ResultVO updateStatus(
            @PathVariable Integer id,
            @PathVariable Boolean bannerStatus){
        Banner banner = new Banner();
        banner.setId(id);
        if (bannerStatus) {
            banner.setBannerStatus(1);
        }else {
            banner.setBannerStatus(0);
        }
        this.bannerService.updateById(banner); // 新创建出来的字段里面只有id和status 其他值为null，但是不会覆盖原有的值
        return ResultVOUtil.success(null);
    }
}

