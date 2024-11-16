package com.webecommerce.dto.discount;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webecommerce.dto.ProductDTO;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductDiscountDTO extends DiscountDTO {

    private ProductDTO product;

    public ProductDTO getProduct() {
        return product;
    }


    public void setProduct(ProductDTO product) {
        this.product = product;
    }

}

