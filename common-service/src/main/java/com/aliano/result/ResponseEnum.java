package com.aliano.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author Cure
 * @Time 2022/5/19 20:06
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
    STOCK_ERROR(300,"库存不足"),
    MOBILE_ERROR(301,"手机号格式错误"),
    MOBILE_EXIST(302,"手机号已注册"),
    MOBILE_IS_NULL(303,"手机号未注册"),
    PASSWORD_ERROR(304,"密码错误"),
    TOKEN_ERROR(305,"Token失效"),
    SMS_SEND_ERROR(306,"短信发送失败"),
    SMS_CODE_ERROR(307,"验证码错误"),
    USERNAME_ERROR(308,"用户名不存在"),
    USERNAME_EXIST(309,"用户名已存在");

    private Integer code;
    private String msg;
}
