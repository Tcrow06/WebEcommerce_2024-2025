package com.webecommerce.service;

import com.webecommerce.constant.EnumOrderStatus;

public interface IOrderStatusService {
    boolean changeStatus(Long orderDetailId, EnumOrderStatus status);

}
