package com.webecommerce.service.impl;

import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.dto.OrderStatusDTO;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.mapper.Impl.OrderStatusMapper;
import com.webecommerce.service.IOrderStatusService;
import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


public class OrderStatusService implements IOrderStatusService {
    @Inject
    private IOrderStatusDAO orderStatusDAO;

    @Inject
    private OrderStatusMapper orderStatusMapper;

    @Override
    public List<OrderStatusDTO> getDeliveredOrdersByCustomerId(Long idUser){
        List<OrderStatusEntity> list = orderStatusDAO.getDeliveredOrdersByCustomerId(idUser);
        return orderStatusMapper.toDTOList(list);
    }
}
