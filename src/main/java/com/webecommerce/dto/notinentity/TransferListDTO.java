package com.webecommerce.dto.notinentity;

import com.webecommerce.dto.BaseDTO;

import java.time.LocalDate;

public class TransferListDTO{
    private Long id;
    private LocalDate returnDate;
    private String nameProduct;
    private String colorProduct;
    private Long quantity;
    private int status;

    private Long orderId;

    public TransferListDTO(Long id, LocalDate returnDate, String nameProduct, String colorProduct, Long quantity, int status, Long orderId) {
        this.id = id;
        this.returnDate = returnDate;
        this.nameProduct = nameProduct;
        this.colorProduct = colorProduct;
        this.quantity = quantity;
        this.status = status;
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getColorProduct() {
        return colorProduct;
    }

    public void setColorProduct(String colorProduct) {
        this.colorProduct = colorProduct;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
