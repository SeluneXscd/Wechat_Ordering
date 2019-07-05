package com.selune.wechatordering.enums;

import lombok.Getter;

/**
 * @Author: Selune
 * @Date: 5/14/19 4:13 PM
 */

@Getter
public enum PayStatusEnum implements CodeEnum {

    /**
     * 0 未支付
     * 1 支付成功
     */
    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
