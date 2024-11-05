package com.webecommerce.dto;


public class ProductDiscountDTO extends DiscountDTO {
    private boolean isOutStanding ;

    private ProductDTO product;


    public ProductDTO getProduct() {
        return product;
    }

    public boolean getIsOutStanding() {
        return isOutStanding;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }



    public void setOutStanding(boolean outStanding) {
        isOutStanding = outStanding;
    }
}

