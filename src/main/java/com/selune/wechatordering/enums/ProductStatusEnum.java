package com.selune.wechatordering.enums;

import lombok.Getter;

/**
 * @Author: Selune
 * @Date: 5/14/19 2:06 PM
 */

@Getter
public enum ProductStatusEnum implements CodeEnum {

    /**
     * UP 上架
     * DOWN 下架
     */
    UP(0, "上架"),
    DOWN(1, "下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
