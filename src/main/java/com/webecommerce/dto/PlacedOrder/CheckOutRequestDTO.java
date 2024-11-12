package com.webecommerce.dto.PlacedOrder;

import java.util.List;

public class CheckOutRequestDTO {
    private List<ProductOrderDTO> selectedProductsId;
    private String billDiscountCode;

    public CheckOutRequestDTO() {
    }

    public CheckOutRequestDTO(List<ProductOrderDTO> selectedProductsId, String billDiscountCode) {
        this.selectedProductsId = selectedProductsId;
        this.billDiscountCode = billDiscountCode;
    }
// Getters và Setters

    public List<ProductOrderDTO> getSelectedProductsId() {
        return selectedProductsId;
    }

    public void setSelectedProductsId(List<ProductOrderDTO> selectedProductsId) {
        this.selectedProductsId = selectedProductsId;
    }

    public String getBillDiscountCode() {
        return billDiscountCode;
    }

    public void setBillDiscountCode(String billDiscountCode) {
        this.billDiscountCode = billDiscountCode;
    }
}

