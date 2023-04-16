package com.aliano.controller;


import com.aliano.entity.User;
import com.aliano.exception.ShopException;
import com.aliano.form.UserForm;
import com.aliano.result.ResponseEnum;
import com.aliano.service.UserService;
import com.aliano.util.JwtUtil;
import com.aliano.util.MD5Util;
import com.aliano.util.RegexValidateUtil;
import com.aliano.util.ResultVOUtil;
import com.aliano.vo.ResultVO;
import com.aliano.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**9
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aliano
 * @since 2022-05-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    //注册用户
    @PostMapping("/register")
    public ResultVO register(@RequestBody UserForm userForm) {
        boolean b = RegexValidateUtil.checkMobile(userForm.getMobile());
        if (!b) {
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", userForm.getMobile());
        User one = this.userService.getOne(queryWrapper);
        if (one != null) {
            throw new ShopException(ResponseEnum.MOBILE_EXIST.getMsg());
        }
        //验证码校验
        String code = (String) this.redisTemplate.opsForValue().get("curestore-sms-code-" + userForm.getMobile());
        if (!code.equals(userForm.getCode())) {
            throw new ShopException(ResponseEnum.SMS_CODE_ERROR.getMsg());
        }

        User user = new User();
        user.setMobile(userForm.getMobile());
        user.setPassword(MD5Util.getSaltMD5(userForm.getPassword()));
        this.userService.save(user);
        return ResultVOUtil.success(null);
    }

    //用户登录
    @GetMapping("/login")
    public ResultVO login(UserForm userForm) { // get请求不能传json 所以不需要加@RequestBody
        boolean b = RegexValidateUtil.checkMobile(userForm.getMobile());
        if (!b) {
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", userForm.getMobile());
        User one = this.userService.getOne(queryWrapper);
        if (one == null) {
            throw new ShopException(ResponseEnum.MOBILE_IS_NULL.getMsg());
        }
        //验证密码
        if (!MD5Util.getSaltverifyMD5(userForm.getPassword(), one.getPassword())) {
            throw new ShopException(ResponseEnum.PASSWORD_ERROR.getMsg());
        }
        //生成token
        String token = JwtUtil.createToken(one.getUserId(), one.getMobile());
        UserVO userVO = new UserVO(one.getUserId(), one.getMobile(), one.getPassword(), token);
        return ResultVOUtil.success(userVO);
    }

    //Token验证
    @GetMapping("/checkToken/{token}")
    public ResultVO checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        boolean result = JwtUtil.checkToken(token);
        if (result) return ResultVOUtil.success(null);
        return ResultVOUtil.fail(ResponseEnum.TOKEN_ERROR.getMsg());
    }

}