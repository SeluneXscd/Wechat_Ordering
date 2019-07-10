package com.selune.wechatordering.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.selune.wechatordering.enums.ProductStatusEnum;
import com.selune.wechatordering.utils.EnumUtils;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Selune
 * @Date: 5/14/19 1:03 PM
 */

@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = -1866609466018862106L;

    @Id
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

    /** 商品状态 0. 正常 1. 下架 */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtils.getByCode(productStatus, ProductStatusEnum.class);
    }
}
