package com.selune.wechatordering.dto;

import lombok.Data;

/**
 * 购物车
 * @Author: Selune
 * @Date: 5/14/19 7:11 PM
 */

@Data
public class CartDTO {

    /** 商品id */
    private String productId;

    /** 下单商品数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
