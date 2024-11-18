package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.OrderDTO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;
import java.util.List;

public class OrderMapper implements GenericMapper<OrderDTO,OrderEntity> {
    @Inject
    private OrderInfoMapper orderInfoMapper;

    @Inject
    private OrderDetailMapper orderDetailMapper;

    @Inject
    private BillDiscountMapper billDiscountMapper;
    @Inject
    private OrderStatusMapper orderStatusMapper;


    @Override
    public OrderDTO toDTO(OrderEntity orderEntity) {
        if (orderEntity == null) return null;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderInfoDTO(orderInfoMapper.toDTO(orderEntity.getOrderInfo()));
        orderDTO.setOrderDetails(orderDetailMapper.toDTOList(orderEntity.getOrderDetails()));
        orderDTO.setBillDiscount(billDiscountMapper.toDTO(orderEntity.getBillDiscount()));
        orderDTO.setOrderStatuses(orderStatusMapper.toDTOList(orderEntity.getOrderStatuses()));
        orderDTO.setShippingFee(orderEntity.getShippingFee());
        orderDTO.setPaymentMethod(orderEntity.getPaymentMethod());
        return orderDTO;
    }


    @Override
    public OrderEntity toEntity(OrderDTO orderDTO) {
        if (orderDTO == null) return null;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderInfo(orderInfoMapper.toEntity(orderDTO.getOrderInfoDTO()));
        orderEntity.setOrderDetails(orderDetailMapper.toEntityList(orderDTO.getOrderDetails()));
        orderEntity.setBillDiscount(billDiscountMapper.toEntity(orderDTO.getBillDiscount()));
        orderEntity.setShippingFee(orderDTO.getShippingFee());
        orderEntity.setPaymentMethod(orderDTO.getPaymentMethod());
        return orderEntity;
    }

    @Override
    public List<OrderDTO> toDTOList(List<OrderEntity> orderEntities) {
        return GenericMapper.super.toDTOList(orderEntities);
    }

    @Override
    public List<OrderEntity> toEntityList(List<OrderDTO> orderDTOS) {
        return GenericMapper.super.toEntityList(orderDTOS);
    }
}
