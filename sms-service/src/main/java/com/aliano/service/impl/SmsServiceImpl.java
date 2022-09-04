package com.aliano.service.impl;

import com.aliano.exception.ShopException;
import com.aliano.result.ResponseEnum;
import com.aliano.service.SmsService;
import com.aliano.util.SmsUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.wxapi.WxApiCall.WxApiCall;
import com.wxapi.model.RequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Cure
 * @Time 2022/5/22 20:09
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Override
    public boolean send(String mobile, String code) {
        RequestModel model = new RequestModel();
        model.setGwUrl(SmsUtil.Url);
        model.setAppkey(SmsUtil.Appkey);
        Map queryMap = new HashMap();
        queryMap.put("sign",SmsUtil.Sign); //访问参数
        queryMap.put("mobile",mobile); //访问参数
        queryMap.put("content","您本次的验证码是："+code); //访问参数
        model.setQueryParams(queryMap);
        try {
            WxApiCall call = new WxApiCall();
            call.setModel(model);
            call.request();
            String request = call.request();
            Gson gson = new Gson();
            Map<String,String> map = gson.fromJson(request,
                    new TypeToken<Map<String,String>>(){}.getType());
            System.out.println(map);
            if(map.get("code").equals("10010"))return true;
        } catch (JsonSyntaxException e) {
            log.error("短信调用失败！");
            throw new ShopException(ResponseEnum.SMS_SEND_ERROR.getMsg());
        }
        return false;
    }
}
