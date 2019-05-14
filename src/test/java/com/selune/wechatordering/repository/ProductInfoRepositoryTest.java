package com.selune.wechatordering.repository;

import com.selune.wechatordering.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Selune
 * @Date: 5/14/19 1:46 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123459");
        productInfo.setProductName("麻辣香锅");
        productInfo.setProductPrice(new BigDecimal(100));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("麻辣香锅");
        productInfo.setProductIcon("https://www.baidu.com");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(6);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());

    }
}