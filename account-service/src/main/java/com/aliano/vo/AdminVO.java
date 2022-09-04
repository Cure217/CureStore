package com.aliano.vo;

import lombok.Data;

/**
 * @Author Cure
 * @Time 2022/5/24 19:09
 */
@Data
public class AdminVO {
    private Integer adminId;
    private String username;
    private String password;
    private String imgUrl;
    private String name;
    private String token;
}
