package com.selune.wechatordering.service;

import com.selune.wechatordering.dto.OrderDTO;

/**
 * @Author: Selune
 * @Date: 5/20/19 7:17 PM
 */

public interface BuyerService {

    // 查询一个订单
    OrderDTO findOneOrder(String openid, String orderId);

    // 取消订单
    OrderDTO cancleOrder(String openid, String orderId);
}
