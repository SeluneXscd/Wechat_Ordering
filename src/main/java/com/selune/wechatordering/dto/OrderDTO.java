package com.selune.wechatordering.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.selune.wechatordering.pojo.OrderDetail;
import com.selune.wechatordering.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO: 数据传输对象 Data Transfer Object
 * @Author: Selune
 * @Date: 5/14/19 6:39 PM
 */

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) // 当字段为空时，不返回
//@JsonInclude(JsonInclude.Include.NON_NULL) // 当字段为空时，不返回
public class OrderDTO {

    /** 订单id */
    private String orderId;

    /** 买家姓名 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信 openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态 默认未支付 */
    private Integer orderStatus;

    /** 支付状态 */
    private Integer payStatus;

    /** 创建时间
     *  Json 时间转换
     *  Date -> Long
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 更新时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList = new ArrayList<>();
}
