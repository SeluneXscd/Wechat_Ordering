package com.selune.wechatordering.repository;

import com.selune.wechatordering.enums.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Selune
 * @Date: 5/14/19 4:57 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567890");
        orderDetail.setOrderId("123456789");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("守望先锋");
        orderDetail.setProductPrice(new BigDecimal(198));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("xxxxxxxxx");

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetail = repository.findByOrderId("123456780");
        Assert.assertNotEquals(0, orderDetail.size());
    }
}