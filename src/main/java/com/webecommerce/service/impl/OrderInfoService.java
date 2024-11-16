package com.webecommerce.service.impl;

import com.webecommerce.dao.order.IOrderInfoDAO;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.mapper.Impl.OrderInfoMapper;
import com.webecommerce.service.IOrderInfoService;

import javax.inject.Inject;

public class OrderInfoService implements IOrderInfoService {
    @Inject
    private IOrderInfoDAO orderInfoDAO;

    @Inject
    private OrderInfoMapper orderInfoMapper;

    @Override
    public OrderInfoDTO findDefaultOrderInfoByIdUser(Long idUser) {
        return orderInfoMapper.toDTO(orderInfoDAO.findDefaultOrderInfoByUserId(idUser));
    }
}
