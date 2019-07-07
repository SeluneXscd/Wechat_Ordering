package com.selune.wechatordering.controller;

import com.selune.wechatordering.enums.ResultEnum;
import com.selune.wechatordering.exception.WeChatOrderException;
import com.selune.wechatordering.form.ProductForm;
import com.selune.wechatordering.pojo.ProductCategory;
import com.selune.wechatordering.pojo.ProductInfo;
import com.selune.wechatordering.service.ProductCategoryService;
import com.selune.wechatordering.service.ProductInfoService;
import com.selune.wechatordering.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 商品信息
 * @Author: Selune
 * @Date: 7/6/19 3:06 PM
 */

@Controller
@RequestMapping("/seller/product")
public class SellProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {

        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {

        try {
            productInfoService.onSale(productId);
        } catch (WeChatOrderException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ON_SALE_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {

        try {
            productInfoService.offSale(productId);
        } catch (WeChatOrderException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.OFF_SALE_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 修改添加商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {

        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        // 查询所有的类目
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);

        return new ModelAndView("product/index", map);
    }

    /**
     * 保存/更新提交
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();

        try {
            // productId 为空，为新增
            if (!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productInfoService.findOne(productForm.getProductId());
            } else {
                productForm.setProductId(KeyUtil.getProductId());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productInfoService.save(productInfo);
        } catch (WeChatOrderException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.PRODUCT_UPDATE_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
