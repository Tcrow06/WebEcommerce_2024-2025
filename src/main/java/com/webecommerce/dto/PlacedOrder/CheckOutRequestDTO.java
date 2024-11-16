package com.webecommerce.dto.PlacedOrder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckOutRequestDTO {
    private Long idUser;
    private List<ProductOrderDTO> selectedProductsId;
    private String billDiscountCode;

    public CheckOutRequestDTO() {
    }

    public CheckOutRequestDTO(List<ProductOrderDTO> selectedProductsId, String billDiscountCode) {
        this.selectedProductsId = selectedProductsId;
        this.billDiscountCode = billDiscountCode;
    }
// Getters và Setters


    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

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

