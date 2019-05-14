package com.selune.wechatordering.enums;

import lombok.Getter;

/**
 * @Author: Selune
 * @Date: 5/14/19 4:10 PM
 */

@Getter
public enum OrderStatusEnum {


    /**
     * 0 新下单
     * 1 完结订单
     * 2 已取消
     */
    NEW(0, "新订单"),
    FINISH(1, "完结"),
    CANCEL(2, "已取消")
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
