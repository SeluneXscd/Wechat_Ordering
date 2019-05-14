package com.selune.wechatordering.converter;

import com.selune.wechatordering.dto.OrderDTO;
import com.selune.wechatordering.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderMaster 转换成 OrderDTO
 * @Author: Selune
 * @Date: 5/14/19 8:20 PM
 */

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO conver(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> conver(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream()
                .map(e -> conver(e))
                .collect(Collectors.toList());
    }
}
