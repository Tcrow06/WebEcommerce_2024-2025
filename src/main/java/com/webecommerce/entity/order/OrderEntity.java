package com.webecommerce.entity.order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "order_entity")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "base_amount")
    private double baseAmount;

    @Column(name = "shipping_fee")
    private double shippingFee;

    @Column(name = "reduced_fee")
    private double reducedFee;

    @OneToOne
    @JoinColumn(name = "order_info_id")
    private OrderInfoEntity orderInfo;

    // Danh sách các chi tiết order
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    // Danh sách status của order này
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderStatusEntity> orderStatuses = new ArrayList<>();

    public List<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderStatusEntity> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatusEntity> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public double getReducedFee() {
        return reducedFee;
    }

    public void setReducedFee(double reducedFee) {
        this.reducedFee = reducedFee;
    }
}
