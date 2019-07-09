package com.selune.wechatordering.service;

import com.selune.wechatordering.dto.OrderDTO;

/**
 * 消息推送
 * @Author: Selune
 * @Date: 7/9/19 8:37 AM
 */

public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
