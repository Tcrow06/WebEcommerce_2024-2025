package com.webecommerce.dto.notinentity;

public class RevenueDTO {
    private Long productId;
    private String productName;
    private Long purchaseQuantity;
    private Double totalRevenue;
    private Long remainingQuantity;

    public RevenueDTO(Long productId, String productName, Long purchaseQuantity, Double totalRevenue, Long remainingQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.purchaseQuantity = purchaseQuantity;
        this.totalRevenue = totalRevenue;
        this.remainingQuantity = remainingQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Long purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Long getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Long remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }
}

