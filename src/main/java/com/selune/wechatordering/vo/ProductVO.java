package com.selune.wechatordering.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品包含类目
 * @Author: Selune
 * @Date: 5/14/19 2:50 PM
 */

@Data
public class ProductVO<T> {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
