package com.aliano.service;

import com.aliano.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author Cure
 * @Time 2022/5/22 20:00
 */
public interface SmsService {
    public boolean send(String mobile,String code);
}
