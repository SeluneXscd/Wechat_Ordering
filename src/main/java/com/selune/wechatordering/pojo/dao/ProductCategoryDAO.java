package com.selune.wechatordering.pojo.dao;

import com.selune.wechatordering.pojo.ProductCategory;
import com.selune.wechatordering.pojo.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Author: Selune
 * @Date: 7/9/19 3:27 PM
 */

public class ProductCategoryDAO {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public int insertByMap(Map<String, Object> map) {
        return productCategoryMapper.insertByMap(map);
    }

    public List<ProductCategory> findByCategoryName(String categoryName){
        return productCategoryMapper.findByCategoryName(categoryName);
    }
}
