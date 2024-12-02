package com.webecommerce.service;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dto.notinentity.TransferListOderStatusDTO;

import java.util.List;

public interface IOrderStatusService {
    boolean changeStatus(Long orderDetailId, EnumOrderStatus status);
    List<TransferListOderStatusDTO> getStatusOrders(Long UserID, EnumOrderStatus status);

}
