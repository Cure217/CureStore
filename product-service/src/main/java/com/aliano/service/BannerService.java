package com.aliano.service;

import com.aliano.entity.Banner;
import com.aliano.vo.BuyerProductCategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aliano
 * @since 2023-05-05
 */
public interface BannerService extends IService<Banner> {
    public List<Banner> bannerList();

    public void addBanner(Banner banner);
}
