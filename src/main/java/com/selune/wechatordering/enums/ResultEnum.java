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
     * 40 订单详情不存在
     * 50 订单状态不正确
     * 60 订单更新失败
     * 70 订单中无商品详情
     * 80 订单支付状态不正确
     */
    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(20, "库存不正确"),

    ORDER_NOT_EXIST(30, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(40, "订单详情不存在"),

    ORDER_STATUS_ERROR(50, "订单状态不正确"),

    ORDER_UPDATE_FAIL(60, "订单更新失败"),

    ORDER_DETAIL_EMPTY(70, "订单中无商品详情"),

    ORDER_PAY_STATUS(80, "订单支付状态不正确")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
