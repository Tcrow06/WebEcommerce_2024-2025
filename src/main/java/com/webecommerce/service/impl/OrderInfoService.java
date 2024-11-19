package com.webecommerce.service.impl;

import com.webecommerce.dao.order.IOrderInfoDAO;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.mapper.Impl.OrderInfoMapper;
import com.webecommerce.service.IOrderInfoService;

import javax.inject.Inject;
import com.webecommerce.entity.order.OrderInfoEntity;
import java.util.List;
import java.util.Optional;


public class OrderInfoService implements IOrderInfoService {
    @Inject
    private IOrderInfoDAO orderInfoDAO;

    @Inject
    private OrderInfoMapper orderInfoMapper;


    @Override
    public OrderInfoDTO findDefaultOrderInfoByIdUser(Long idUser) {
        return orderInfoMapper.toDTO(orderInfoDAO.findDefaultOrderInfoByUserId(idUser));
    }

    @Override
    public OrderInfoDTO getOrderInfoById(Long orderInfoId) {
        return orderInfoMapper.toDTO(orderInfoDAO.findById(orderInfoId));
    }

    @Override
    public OrderInfoDTO addOrderInfo(OrderInfoDTO orderInfo) {
        return orderInfoMapper.toDTO(orderInfoDAO.insert(orderInfoMapper.toEntity(orderInfo)));
    }

    @Override
    public OrderInfoDTO updateOrderInfo(OrderInfoDTO orderInfo) {
        OrderInfoEntity oldOrderInfo = orderInfoDAO.findById(orderInfo.getId());
        if (oldOrderInfo != null) {
            return orderInfoMapper.toDTO(orderInfoDAO.update(orderInfoMapper.toEntity(orderInfo)));
        }
        return null;
    }

    @Override
    public OrderInfoDTO getOrderInfoDefault(Long customerId) {
        List<OrderInfoDTO> orderInfos = getOrderInfoByCustomerId(customerId);
        Optional<OrderInfoDTO> orderInfoDefault = orderInfos.stream()
                .filter(orderInfo -> orderInfo.getIsDefault() == 1)
                .findFirst();
        return orderInfoDefault.isPresent() ? orderInfoDefault.get() : null;
    }

    @Override
    public boolean deleteOrderInfo(Long id) {
        return orderInfoDAO.delete(id);
    }

    @Override
    public boolean setOrderInfoDefault(OrderInfoDTO orderInfo) {
        return orderInfoDAO.setOrderInfoDefault(orderInfoMapper.toEntity(orderInfo));
    }

    @Override
    public List<OrderInfoDTO> getAllOrderInfos() {
        return orderInfoMapper.toDTOList(orderInfoDAO.findAll());
    }

    @Override
    public List<OrderInfoDTO> getOrderInfoByCustomerId(Long customerId) {
        return orderInfoMapper.toDTOList(orderInfoDAO.findOrderInfoByCustomerId(customerId));
    }
}
