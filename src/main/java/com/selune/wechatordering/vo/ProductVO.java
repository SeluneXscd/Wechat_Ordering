package com.selune.wechatordering.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品包含类目
 * @Author: Selune
 * @Date: 5/14/19 2:50 PM
 */

@Data
public class ProductVO<T> implements Serializable {

    private static final long serialVersionUID = -6188185011284782470L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
