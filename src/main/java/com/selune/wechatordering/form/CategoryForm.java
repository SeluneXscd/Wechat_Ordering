package com.selune.wechatordering.form;

import lombok.Data;

/**
 * @Author: Selune
 * @Date: 7/7/19 10:41 AM
 */

@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;
}
