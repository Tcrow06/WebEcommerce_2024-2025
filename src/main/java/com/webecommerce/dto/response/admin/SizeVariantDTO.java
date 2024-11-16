package com.webecommerce.dto.response.admin;

public class SizeVariantDTO {
    private Long id;

    private String size;

    private int quantity;

    public SizeVariantDTO(String size, int quantity, double price, Long id) {
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
    }

    public SizeVariantDTO() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
