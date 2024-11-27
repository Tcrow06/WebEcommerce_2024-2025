package com.webecommerce.dto.notinentity;

import com.webecommerce.constant.EnumOrderStatus;

public class DisplayOrderDetailDTO {
    private Long id;
    private Long quantity;
    private String imageUrl;
    private Double price;
    private String color;
    private String size;
    private String productName;
    private EnumOrderStatus status;

    public DisplayOrderDetailDTO(Long id, Long quantity, String imageUrl, String color, String size, String productName, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.price = price;
        this.color = color;
        this.size = size;
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }
}
