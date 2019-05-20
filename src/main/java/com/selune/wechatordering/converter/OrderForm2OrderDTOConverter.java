package com.selune.wechatordering.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.selune.wechatordering.dto.OrderDTO;
import com.selune.wechatordering.enums.ResultEnum;
import com.selune.wechatordering.exception.WeChatOrderException;
import com.selune.wechatordering.form.OrderForm;
import com.selune.wechatordering.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Selune
 * @Date: 5/20/19 2:30 PM
 */

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO conver(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            // json转换 String -> List
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换】转换错误，string= {}", orderForm.getItems());
            throw new WeChatOrderException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
