package com.selune.wechatordering.service.impl;

import com.selune.wechatordering.converter.OrderMaster2OrderDTOConverter;
import com.selune.wechatordering.dto.CartDTO;
import com.selune.wechatordering.dto.OrderDTO;
import com.selune.wechatordering.enums.OrderDetail;
import com.selune.wechatordering.enums.OrderStatusEnum;
import com.selune.wechatordering.enums.PayStatusEnum;
import com.selune.wechatordering.enums.ResultEnum;
import com.selune.wechatordering.exception.WeChatOrderException;
import com.selune.wechatordering.pojo.OrderMaster;
import com.selune.wechatordering.pojo.ProductInfo;
import com.selune.wechatordering.repository.OrderDetailRepository;
import com.selune.wechatordering.repository.OrderMasterRepository;
import com.selune.wechatordering.service.OrderService;
import com.selune.wechatordering.service.ProductInfoService;
import com.selune.wechatordering.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Selune
 * @Date: 5/14/19 6:45 PM
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 1. 查询商品( 数量, 价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (null == productInfo) {
                throw new WeChatOrderException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            // 3. 订单详情入库 ( orderDetail)
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);

            orderDetailRepository.save(orderDetail);
        }


        // 3. 主订单入库( orderMaster)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 4. 扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                        .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                        .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (null == orderMaster) {
            throw new WeChatOrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new WeChatOrderException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,
                pageable);

        List<OrderDTO> orderDTOList =
                OrderMaster2OrderDTOConverter.conver(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage =
                new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());


        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
