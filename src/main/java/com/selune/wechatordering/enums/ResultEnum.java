package com.selune.wechatordering.enums;

import lombok.Getter;

/**
 * @Author: Selune
 * @Date: 5/14/19 6:49 PM
 */

@Getter
public enum ResultEnum {

    /**
     * 10 商品不存在
     * 20 库存不正确
     * 30 订单不存在
     * 40
     */
    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(20, "库存不正确"),

    ORDER_NOT_EXIST(30, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(40, "订单详情不存在")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
