package com.aliano.service.impl;

import com.aliano.entity.Banner;
import com.aliano.mapper.BannerMapper;
import com.aliano.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aliano
 * @since 2023-05-05
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    public BannerMapper bannerMapper;

    @Override
    public List<Banner> bannerList() {
        return null;
    }

    @Override
    public void addBanner(Banner banner) {
        this.bannerMapper.insert(banner);
    }
}
