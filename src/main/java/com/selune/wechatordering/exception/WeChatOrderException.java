package com.selune.wechatordering.exception;

import com.selune.wechatordering.enums.ResultEnum;

/**
 * @Author: Selune
 * @Date: 5/14/19 6:49 PM
 */

public class WeChatOrderException extends RuntimeException {

    private Integer code;

    public WeChatOrderException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public WeChatOrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
