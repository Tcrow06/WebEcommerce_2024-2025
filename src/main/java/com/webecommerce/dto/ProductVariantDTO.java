package com.webecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webecommerce.constant.EnumProductStatus;

import javax.servlet.http.Part;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantDTO extends BaseDTO <ProductVariantDTO> {
    private Long id;

    private double price;

    private EnumProductStatus status;

    private String imageUrl;

    private String color;

    private String size;

    private int quantity;

    private String name;

    private Part image;

    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
}
