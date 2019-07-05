package com.selune.wechatordering.controller;

import com.lly835.bestpay.model.PayResponse;
import com.selune.wechatordering.dto.OrderDTO;
import com.selune.wechatordering.enums.ResultEnum;
import com.selune.wechatordering.exception.WeChatOrderException;
import com.selune.wechatordering.service.OrderService;
import com.selune.wechatordering.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: Selune
 * @Date: 5/30/19 4:43 PM
 */

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     * 创建支付
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        // 1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (null == orderDTO) {
            throw new WeChatOrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse", "1558336670489");

        return new ModelAndView("pay/create", map);
    }
}
