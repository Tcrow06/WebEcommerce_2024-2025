package com.webecommerce.dto;

import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.product.ProductEntity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class ProductDiscountDTO extends DiscountDTO {
    private boolean isOutStanding ;

    private ProductDTO product;


    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public boolean isOutStanding() {
        return isOutStanding;
    }

    public void setOutStanding(boolean outStanding) {
        isOutStanding = outStanding;
    }
}

