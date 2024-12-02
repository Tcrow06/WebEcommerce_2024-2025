package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.dto.OrderStatusDTO;
import com.webecommerce.dto.notinentity.TransferListOderStatusDTO;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.mapper.Impl.OrderStatusMapper;
import com.webecommerce.service.IOrderStatusService;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

public class OrderStatusService implements IOrderStatusService {
    @Inject
    private IOrderStatusDAO orderStatusDAO;
    @Inject
    private IOrderDAO orderDAO;
    @Inject
    private OrderStatusMapper orderStatusMapper;
    @Override
    public boolean changeStatus(Long orderDetailId, EnumOrderStatus status) {
        long result = orderStatusDAO.changeStatus(orderDetailId, status);
        long orderId = orderDAO.findOrderId(orderDetailId);

        if (result == 0) {
            OrderStatusEntity newOrderStatus = new OrderStatusEntity();
            newOrderStatus.setOrder(orderDAO.findById(orderId));
            newOrderStatus.setStatus(status);
            newOrderStatus.setDate(LocalDateTime.now());

            OrderStatusDTO dto = orderStatusMapper.toDTO(orderStatusDAO.insert(newOrderStatus));
            return dto != null;
        }
        return false;
    }
    @Override
    public List<TransferListOderStatusDTO> getStatusOrders(Long UserID, EnumOrderStatus status) {
        return orderStatusDAO.getStatusOrders(UserID, status);
    }
}
