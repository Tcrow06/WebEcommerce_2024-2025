package com.webecommerce.dto.notinentity;

import java.time.LocalDate;
import java.util.UUID;

public class ProductReturnDTO {
    private Long id;

    private LocalDate returnDate;
    private int status;
    private Long quantityReturn;
    private String color;
    private String imgUrl;
    private String size;
    private String productName;
    private double salePrice;
    private String reason;

    public ProductReturnDTO(Long id, LocalDate returnDate, int status, Long quantityReturn, String color, String imgUrl, String size, String productName, double salePrice, String reason) {
        this.id = id;
        this.returnDate = returnDate;
        this.status = status;
        this.quantityReturn = quantityReturn;
        this.color = color;
        this.imgUrl = imgUrl;
        this.size = size;
        this.productName = productName;
        this.salePrice = salePrice;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getQuantityReturn() {
        return quantityReturn;
    }

    public void setQuantityReturn(Long quantityReturn) {
        this.quantityReturn = quantityReturn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
