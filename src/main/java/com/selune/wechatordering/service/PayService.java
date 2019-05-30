package com.selune.wechatordering.service;

import com.selune.wechatordering.dto.OrderDTO;

/**
 * @Author: Selune
 * @Date: 5/30/19 4:47 PM
 */

public interface PayService {

    void create(OrderDTO orderDTO);
}
