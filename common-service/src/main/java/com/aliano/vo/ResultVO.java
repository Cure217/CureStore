package com.aliano.vo;

/**
 * @Author Cure
 * @Time 2022/5/19 15:12
 */
import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
