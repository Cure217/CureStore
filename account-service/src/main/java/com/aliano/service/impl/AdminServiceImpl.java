package com.aliano.service.impl;

import com.aliano.entity.Admin;
import com.aliano.mapper.AdminMapper;
import com.aliano.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aliano
 * @since 2022-05-22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
