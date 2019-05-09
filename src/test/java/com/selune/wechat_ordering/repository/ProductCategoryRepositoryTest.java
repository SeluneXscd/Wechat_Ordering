package com.selune.wechat_ordering.repository;

import com.selune.wechat_ordering.pojo.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

	@Autowired
	private ProductCategoryRepository repository;

	@Test
	public void findOneTest() {
		ProductCategory productCategory = repository.findOne(1);
		System.out.println(productCategory.toString());
	}
}