package com.webecommerce.entity.order;

import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.people.CustomerEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "[order]")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipping_fee")
    private double shippingFee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_discount_id", referencedColumnName = "id")
    private BillDiscountEntity billDiscount;

    @OneToMany(mappedBy = "order")
    private List<OrderStatusEntity> orderStatuses;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_info_id", referencedColumnName = "id")
    private OrderInfoEntity orderInfo;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    public BillDiscountEntity getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(BillDiscountEntity billDiscount) {
        this.billDiscount = billDiscount;
    }

    public List<OrderStatusEntity> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatusEntity> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public List<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderInfoEntity getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoEntity orderInfo) {
        this.orderInfo = orderInfo;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

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
}
