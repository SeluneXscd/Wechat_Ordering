package com.selune.wechatordering.vo;

import lombok.Data;

/**
 * http 请求返回的最外层对象
 * @Author: Selune
 * @Date: 5/14/19 2:35 PM
 */

@Data
public class ResultVO<T> {

    /** 错误码 code */
    private Integer code;

    /** 提示信息 message */
    private String message;

    /** 具体内容 data */
    private T data;
}