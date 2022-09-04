package com.aliano.service.impl;

import com.aliano.entity.User;
import com.aliano.mapper.UserMapper;
import com.aliano.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
