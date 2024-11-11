package com.webecommerce.dto.discount;


import com.webecommerce.dto.ProductDTO;

public class ProductDiscountDTO extends DiscountDTO {

    private ProductDTO product;

    public ProductDTO getProduct() {
        return product;
    }


    public void setProduct(ProductDTO product) {
        this.product = product;
    }



}

