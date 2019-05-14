package com.selune.wechatordering.service;

import com.selune.wechatordering.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: Selune
 * @Date: 5/14/19 2:02 PM
 */

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有上架商品
     * @return Up
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // TODO: 增加库存

    // TODO: 减少库存
}
