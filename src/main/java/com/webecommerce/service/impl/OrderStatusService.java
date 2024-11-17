package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.dto.notinentity.TransferListOderStatusDTO;
import com.webecommerce.service.IOrderStatusService;

import javax.inject.Inject;
import java.util.List;


public class OrderStatusService implements IOrderStatusService {
    @Inject
    private IOrderStatusDAO orderStatusDAO;

    @Override
    public List<TransferListOderStatusDTO> getStatusOrders(Long idUser, EnumOrderStatus status){
        return orderStatusDAO.getStatusOrders(idUser, status);
    }
}
