package com.selune.wechatordering.dto;

import com.selune.wechatordering.pojo.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO: 数据传输对象 Data Transfer Object
 * @Author: Selune
 * @Date: 5/14/19 6:39 PM
 */

@Data
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

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
