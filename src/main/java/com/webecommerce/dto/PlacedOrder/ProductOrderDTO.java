package com.webecommerce.dto.PlacedOrder;

public class ProductOrderDTO {
    private Long productVariantId;
    private int quantity;

    // Getters và Setters
    public Long getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Long productVariantId) {
        this.productVariantId = productVariantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
