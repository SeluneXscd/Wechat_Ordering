package com.selune.wechatordering.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: Selune
 * @Date: 7/6/19 7:51 PM
 */

@Data
public class ProductForm {

    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品单价 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private Integer productStock;

    /** 商品描述 */
    private String productDescription;

    /** 商品图片 */
    private String productIcon;

    /** 类目编号 */
    private Integer categoryType;
}
