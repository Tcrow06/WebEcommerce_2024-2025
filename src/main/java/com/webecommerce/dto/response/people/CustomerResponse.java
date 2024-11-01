package com.webecommerce.dto.response.people;

public class CustomerResponse extends UserResponse{

   private String loyaltyPoint;

    public String getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(String loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }
}
