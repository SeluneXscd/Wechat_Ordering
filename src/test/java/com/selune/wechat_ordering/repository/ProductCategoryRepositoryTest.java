package com.selune.wechat_ordering.repository;

import com.selune.wechat_ordering.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

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

	@Test
	@Transactional // 测试中, 完全回滚, 插入数据库之后也能够回滚
	public void saveTest() {
		ProductCategory productCategory = new ProductCategory("玩具", 16);

		ProductCategory result = repository.save(productCategory);
		Assert.assertNotNull(result);
	}

	@Test
	@Transactional
	public void updateTest() {
		ProductCategory productCategory = repository.findOne(3);
		System.out.println(productCategory.getCategoryName());
		productCategory.setCategoryType(10);

		repository.save(productCategory);
	}

	@Test
	public void findByCategoryTypeIn() {
		List<Integer> list = Arrays.asList(2, 3, 4);
		List<ProductCategory> result = repository.findByCategoryTypeIn(list);

		Assert.assertNotEquals(0, result.size());
	}
}