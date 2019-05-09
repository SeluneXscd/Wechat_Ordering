package com.selune.wechat_ordering.repository;

import com.selune.wechat_ordering.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
