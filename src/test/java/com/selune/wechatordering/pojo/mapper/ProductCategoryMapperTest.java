package com.selune.wechatordering.pojo.mapper;

import com.selune.wechatordering.pojo.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Selune
 * @Date: 7/9/19 11:41 AM
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "好玩榜");
        map.put("category_type", 101);
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("好玩榜");
        productCategory.setCategoryType(102);
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() throws Exception {
        ProductCategory productCategory = productCategoryMapper.findByCategoryType(1);
        Assert.assertEquals("热销榜", productCategory.getCategoryName());
    }

    @Test
    public void findByCategoryName() throws Exception {
        List<ProductCategory> productCategoryList = productCategoryMapper.findByCategoryName("好玩榜");
        Assert.assertEquals(2, productCategoryList.size());
    }

    @Test
    public void updateByCategoryType() throws Exception {
        int result = productCategoryMapper.updateByCategoryType("好玩榜1", 102);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("新好玩榜");
        productCategory.setCategoryType(102);
        int result = productCategoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() throws Exception {
        int result = productCategoryMapper.deleteByCategoryType(102);
        Assert.assertEquals(1, result);
    }
}