package com.webecommerce.dto;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.entity.order.OrderEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

public class OrderStatusDTO {
    private Long id;

    private LocalDateTime date;

    private EnumOrderStatus status;

    public OrderStatusDTO() {
    }
}
