package com.aliano.mapper;

import com.aliano.entity.Banner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author aliano
 * @since 2023-05-05
 */
public interface BannerMapper extends BaseMapper<Banner> {
    public void insertBanner(Banner banner);
}
