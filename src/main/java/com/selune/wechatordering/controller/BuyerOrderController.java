package com.selune.wechatordering.controller;

import com.selune.wechatordering.converter.OrderForm2OrderDTOConverter;
import com.selune.wechatordering.dto.OrderDTO;
import com.selune.wechatordering.enums.ResultEnum;
import com.selune.wechatordering.exception.WeChatOrderException;
import com.selune.wechatordering.form.OrderForm;
import com.selune.wechatordering.service.BuyerService;
import com.selune.wechatordering.service.OrderService;
import com.selune.wechatordering.utils.ResultVOUtil;
import com.selune.wechatordering.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Selune
 * @Date: 5/19/19 6:37 PM
 */

@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    // 创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        // 1. 判断表单校验有没有错误
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数错误，orderForm= {}", orderForm);
            throw new WeChatOrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.conver(orderForm);

        // 2. 判断购物车是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车为空");
            throw new WeChatOrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    // 订单列表
    @GetMapping("list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {

        // 1. 校验，openid不能为空
        if (StringUtils.isEmpty(openid)) {
            log.error("【订单列表】 openid为空");
            throw new WeChatOrderException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    // 订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOneOrder(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    // 取消订单
    @PostMapping("/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        buyerService.cancleOrder(openid, orderId);
        return ResultVOUtil.success();
    }

}
