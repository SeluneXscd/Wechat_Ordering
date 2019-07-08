package com.selune.wechatordering.enums;

import lombok.Getter;

/**
 * @Author: Selune
 * @Date: 5/14/19 6:49 PM
 */

@Getter
public enum ResultEnum {

    /**
     * 0 成功
     * 1 参数不正确
     * 10 商品不存在
     * 11 库存不正确
     * 12 订单不存在
     * 13 订单详情不存在
     * 14 订单状态不正确
     * 15 订单更新失败
     * 16 订单中无商品详情
     * 17 订单支付状态不正确
     * 18 购物车为空
     * 19 该订单不属于当前用户
     * 20 微信用户错误
     */
    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数错误"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "库存不正确"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_DETAIL_EMPTY(16, "订单中无商品详情"),

    ORDER_PAY_STATUS(17, "订单支付状态不正确"),

    CART_EMPTY(18, "购物车为空"),

    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(20, "微信用户错误"),

    ORDER_CANCEL_SUCCESS(21, "订单取消成功"),

    ORDER_FINISH_SUCCESS(22, "订单完结成功"),

    PRODUCT_STATUS_ERROR(23, "商品状态错误"),

    ON_SALE_SUCCESS(24, "上架成功"),

    OFF_SALE_SUCCESS(25, "下架成功"),

    PRODUCT_UPDATE_SUCCESS(26, "商品修改成功"),

    LOGIN_FAIL(27, "登录失败，登录信息不正确"),

    LOGOUT_SUCCESS(28, "登出成功")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
