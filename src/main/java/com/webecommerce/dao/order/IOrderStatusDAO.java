package com.webecommerce.dao.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.notinentity.TransferListOderStatusDTO;
import com.webecommerce.entity.order.OrderStatusEntity;

import java.util.List;

public interface IOrderStatusDAO extends GenericDAO<OrderStatusEntity> {
    List<TransferListOderStatusDTO> getStatusOrders(Long idUser, EnumOrderStatus status);
}
