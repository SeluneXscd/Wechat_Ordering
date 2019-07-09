package com.selune.wechatordering.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 * @Author: Selune
 * @Date: 5/14/19 2:53 PM
 */

@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = 5609080992207231560L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
