package com.aliano.controller;


import com.aliano.entity.Admin;
import com.aliano.entity.User;
import com.aliano.exception.ShopException;
import com.aliano.form.AdminForm;
import com.aliano.form.UserForm;
import com.aliano.result.ResponseEnum;
import com.aliano.service.AdminService;
import com.aliano.service.UserService;
import com.aliano.util.JwtUtil;
import com.aliano.util.MD5Util;
import com.aliano.util.RegexValidateUtil;
import com.aliano.util.ResultVOUtil;
import com.aliano.vo.AdminVO;
import com.aliano.vo.ResultVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aliano
 * @since 2022-05-22
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisTemplate redisTemplate;

    //注册用户
    @PostMapping("/register")
    public ResultVO register(@RequestBody AdminForm adminForm){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",adminForm.getUsername());
        Admin one = this.adminService.getOne(queryWrapper);
        if (one != null) {
            throw new ShopException(ResponseEnum.USERNAME_EXIST.getMsg());
        }
        //验证码校验
        //String code = (String)this.redisTemplate.opsForValue().get("curestore-sms-code-" + adminForm.getUsername());
        //if (!code.equals(adminForm.getCode())) {
        //    throw new ShopException(ResponseEnum.SMS_CODE_ERROR.getMsg());
        //}

        Admin admin = new Admin();
        admin.setUsername(adminForm.getUsername());
        admin.setImgUrl("https://pic.qqtn.com/up/2019-6/2019062708503566550.jpg");
        admin.setPassword(MD5Util.getSaltMD5(adminForm.getPassword()));
        admin.setName("蓝胖子");
        this.adminService.save(admin);
        return ResultVOUtil.success(null);
    }

    //用户登录
    @GetMapping("/login")
    public ResultVO login(AdminForm adminForm){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",adminForm.getUsername());
        Admin one = this.adminService.getOne(queryWrapper);
        if (one == null) {
            throw new ShopException(ResponseEnum.USERNAME_ERROR.getMsg());
        }
        //验证密码
        if (!MD5Util.getSaltverifyMD5(adminForm.getPassword(),one.getPassword())) {
            throw new ShopException(ResponseEnum.PASSWORD_ERROR.getMsg());
        }
        String token = JwtUtil.createToken(one.getAdminId(),one.getUsername());
        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(one,adminVO);
        adminVO.setToken(token);
        return ResultVOUtil.success(adminVO);
    }

    //Token验证
    @GetMapping("/checkToken")
    public ResultVO checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        boolean result = JwtUtil.checkToken(token);
        if(result) return ResultVOUtil.success(null);
        return ResultVOUtil.fail(ResponseEnum.TOKEN_ERROR.getMsg());
    }
}

