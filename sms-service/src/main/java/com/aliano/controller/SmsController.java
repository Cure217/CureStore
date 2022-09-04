package com.aliano.controller;

import com.aliano.exception.ShopException;
import com.aliano.result.ResponseEnum;
import com.aliano.service.SmsService;
import com.aliano.util.RandomUtil;
import com.aliano.util.RegexValidateUtil;
import com.aliano.util.ResultVOUtil;
import com.aliano.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cure
 * @Time 2022/5/22 19:49
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/send/{mobile}")
    public ResultVO send(@PathVariable("mobile") String mobile){
        boolean b = RegexValidateUtil.checkMobile(mobile);
        if (!b) {
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg());
        }
        String code = RandomUtil.getSixBitRandom();
        boolean send = this.smsService.send(mobile,code);
        if (send) {
            //把code存入Redis
            this.redisTemplate.opsForValue().set("curestore-sms-code-"+mobile, code);
            return ResultVOUtil.success("短信发送成功");
        }
        return ResultVOUtil.fail(null);
    }
}
