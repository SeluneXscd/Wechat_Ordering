package com.selune.wechatordering.controller;

import com.selune.wechatordering.pojo.ProductCategory;
import com.selune.wechatordering.pojo.ProductInfo;
import com.selune.wechatordering.service.ProductCategoryService;
import com.selune.wechatordering.service.ProductInfoService;
import com.selune.wechatordering.utils.ResultVOUtil;
import com.selune.wechatordering.vo.ProductInfoVO;
import com.selune.wechatordering.vo.ProductVO;
import com.selune.wechatordering.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Selune
 * @Date: 5/14/19 2:33 PM
 */

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list() {

        // 1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2. 查询类目, 一次性查清
        // 2.1. 传统方法
        /*
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        */

        // 2.2. Lambda
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList =
                productCategoryService.findByCategoryTypeIn(categoryTypeList);

        // 3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        // 3.1 先拼装类目
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

        // 3.2 再拼装商品详情
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
