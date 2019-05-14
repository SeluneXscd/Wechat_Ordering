package com.selune.wechatordering.service.impl;

import com.selune.wechatordering.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Selune
 * @Date: 5/9/19 11:12 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = productCategoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategoryList =
                productCategoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    @Transactional
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("玩具", 20);
        ProductCategory result = productCategoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}