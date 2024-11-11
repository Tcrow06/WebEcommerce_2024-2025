package com.webecommerce.dto;

import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.entity.people.CustomerEntity;

import javax.persistence.*;
import java.util.List;

public class OrderDTO {

    private Long id;

    private double shippingFee;

    private BillDiscountEntity billDiscount;

    private List<OrderStatusDTO> orderStatuses;

    private List<OrderDetailDTO> orderDetails;

    private OrderInfoDTO orderInfoDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BillDiscountEntity getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(BillDiscountEntity billDiscount) {
        this.billDiscount = billDiscount;
    }

    public List<OrderStatusDTO> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatusDTO> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderInfoDTO getOrderInfoDTO() {
        return orderInfoDTO;
    }

    public void setOrderInfoDTO(OrderInfoDTO orderInfoDTO) {
        this.orderInfoDTO = orderInfoDTO;
    }
}
