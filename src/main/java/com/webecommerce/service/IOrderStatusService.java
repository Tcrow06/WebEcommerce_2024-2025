package com.webecommerce.service;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.OrderStatusDTO;
import com.webecommerce.dto.notinentity.TransferListOderStatusDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;


import java.util.List;
public interface IOrderStatusService {
    List<TransferListOderStatusDTO> getStatusOrders(Long customerId, EnumOrderStatus status);
}
