package com.webecommerce.entity.discount;

import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill_discount")
public class BillDiscountEntity extends DiscountEntity {

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "minimum_invoice_amount")
    private double minimumInvoiceAmount;


    @Column(name = "loyaltyPointsRequired")
    private int loyaltyPointsRequired;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "billDiscount")
    private List<OrderEntity> orders = new ArrayList<>();

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

    @Override
    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
