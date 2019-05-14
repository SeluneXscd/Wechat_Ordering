package com.selune.wechatordering.repository;

import com.selune.wechatordering.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: Selune
 * @Date: 5/14/19 4:27 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456781");
        orderMaster.setBuyerName("胖按钮");
        orderMaster.setBuyerPhone("123456789");
        orderMaster.setBuyerAddress("艾兴瓦尔德");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(200));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(1, 5);
        Page<OrderMaster> result = repository.findByBuyerOpenid("110110", pageRequest);
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}