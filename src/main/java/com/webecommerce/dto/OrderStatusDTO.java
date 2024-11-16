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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }
}
