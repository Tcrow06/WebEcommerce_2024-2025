package com.webecommerce.entity.order;

import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;

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

    @OneToOne
    @JoinColumn(name = "order_info_id")
    private OrderInfoEntity orderInfo;

    // Danh sách các chi tiết order
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    // Danh sách status của order này
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderStatusEntity> orderStatuses = new ArrayList<>();

    // Danh sách các vouher
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductDiscountEntity> productDiscounts = new ArrayList<>();

    // Mỗi order sẽ được 1 cái bill discount
    @OneToOne
    @JoinColumn(name = "bill_discount")
    private BillDiscountEntity billDiscount;

    public BillDiscountEntity getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(BillDiscountEntity billDiscount) {
        this.billDiscount = billDiscount;
    }

    public List<ProductDiscountEntity> getProductDiscounts() {
        return productDiscounts;
    }

    public void setProductDiscounts(List<ProductDiscountEntity> productDiscounts) {
        this.productDiscounts = productDiscounts;
    }

    public OrderInfoEntity getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoEntity orderInfo) {
        this.orderInfo = orderInfo;
    }

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
