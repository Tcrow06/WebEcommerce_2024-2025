package com.webecommerce.dto;

import com.webecommerce.constant.EnumProductStatus;


public class ProductVariantDTO extends BaseDTO <ProductVariantDTO> {

    private double price;

    private EnumProductStatus status;

    private String imageUrl;

    private String color;

    private String size;

    private int quantity;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EnumProductStatus getStatus() {
        return status;
    }

    public void setStatus(EnumProductStatus status) {
        this.status = status;
    }
    public void setStatus (String status) {
        this.status = EnumProductStatus.valueOf(status);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
