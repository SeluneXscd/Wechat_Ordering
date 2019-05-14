package com.selune.wechatordering.service;

import com.selune.wechatordering.pojo.ProductCategory;

import java.util.List;

/**
 * @Author: Selune
 * @Date: 5/9/19 11:06 PM
 */

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
