package com.selune.wechatordering.service.impl;

import com.selune.wechatordering.dto.OrderDTO;
import com.selune.wechatordering.enums.ResultEnum;
import com.selune.wechatordering.exception.WeChatOrderException;
import com.selune.wechatordering.service.BuyerService;
import com.selune.wechatordering.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Selune
 * @Date: 5/20/19 7:18 PM
 */

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancleOrder(String openid, String orderId) {

        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (null == orderDTO) {
            log.error("【取消订单】订单不存在，orderId= {}", orderId);
            throw new WeChatOrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {

        OrderDTO orderDTO = orderService.findOne(orderId);
        if (null == orderDTO) {
            return null;
        }
        if (!orderDTO.getOrderId().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致，openid= {}, orderDTO= {}", openid, orderDTO);
            throw new WeChatOrderException(ResultEnum.ORDER_OWNER_ERROR);
        }

        return orderDTO;
    }
}
