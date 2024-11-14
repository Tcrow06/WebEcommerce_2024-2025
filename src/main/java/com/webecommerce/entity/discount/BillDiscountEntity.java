package com.webecommerce.entity.discount;

import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bill_discount")
public class BillDiscountEntity extends DiscountEntity {

    @Column(name = "minimum_invoice_amount")
    private double minimumInvoiceAmount;


    @Column(name = "loyaltyPointsRequired")
    private int loyaltyPointsRequired;

    @Column(name = "code")
    private String code;

    public double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }


    @Column(name = "maximum_amount")
    private double maximumAmount;


    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "billDiscount")
    private List<OrderEntity> orders;


    public double getMinimumInvoiceAmount() {
        return minimumInvoiceAmount;
    }

    public void setMinimumInvoiceAmount(double minimumInvoiceAmount) {
        this.minimumInvoiceAmount = minimumInvoiceAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLoyaltyPointsRequired() {
        return loyaltyPointsRequired;
    }

    public void setLoyaltyPointsRequired(int loyaltyPointsRequired) {
        this.loyaltyPointsRequired = loyaltyPointsRequired;
    }
}
