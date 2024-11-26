package com.webecommerce.entity.order;

import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.people.CustomerEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "[order]")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipping_fee")
    private double shippingFee;

    @Column(name="payment_method")
    private String paymentMethod;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bill_discount_id", referencedColumnName = "id")
    private BillDiscountEntity billDiscount;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderStatusEntity> orderStatuses = new ArrayList<>();

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_info_id", referencedColumnName = "id")
    private OrderInfoEntity orderInfo;

    @ManyToOne(cascade = CascadeType.MERGE)
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


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
