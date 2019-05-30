package com.selune.wechatordering.service.impl;

import com.selune.wechatordering.dto.OrderDTO;
import com.selune.wechatordering.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: Selune
 * @Date: 5/30/19 5:03 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        payService.create(orderDTO);
    }
}