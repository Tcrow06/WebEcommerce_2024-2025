package com.webecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer_discount")
public class CustomerDiscountEntity extends DiscountEntity {

    @Column(name = "loyalty_points_required")
    private int loyaltyPointsRequired;

    public int getLoyaltyPointsRequired() {
        return loyaltyPointsRequired;
    }

    public void setLoyaltyPointsRequired(int loyaltyPointsRequired) {
        this.loyaltyPointsRequired = loyaltyPointsRequired;
    }
}
