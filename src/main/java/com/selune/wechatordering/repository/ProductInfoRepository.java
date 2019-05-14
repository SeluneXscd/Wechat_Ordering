package com.selune.wechatordering.repository;

import com.selune.wechatordering.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Selune
 * @Date: 5/14/19 1:44 PM
 */

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /** 查询上架商品 */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
