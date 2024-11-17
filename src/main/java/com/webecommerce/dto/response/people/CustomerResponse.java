package com.webecommerce.dto.response.people;

public class CustomerResponse extends UserResponse{

   private String loyaltyPoint;

    public CustomerResponse() {
    }

    public CustomerResponse(Long customerId, String customerName, String phone, String email) {
        super(customerId, customerName, phone,email);
    }

    public String getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(String loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }


}
