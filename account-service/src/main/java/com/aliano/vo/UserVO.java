package com.aliano.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Cure
 * @Time 2022/5/22 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Integer userId;
    private String mobile;
    private String password;
    private String token;
}
