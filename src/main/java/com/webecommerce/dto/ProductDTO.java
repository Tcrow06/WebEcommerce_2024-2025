package com.webecommerce.dto;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.CategoryEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO extends BaseDTO<ProductDTO> {

    private String name;

    private boolean highlight;

    private EnumProductStatus status;

    private boolean isNew;

    private String brand;

    private String description;

    private CategoryEntity category;

    private List<ProductVariantDTO> productVariants = new ArrayList<>();

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;


    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public EnumProductStatus getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = EnumProductStatus.valueOf(status);
    }

    public void setStatus(EnumProductStatus status) {
        this.status = status;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductVariantDTO> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariantDTO> productVariants) {
        this.productVariants = productVariants;
    }
}
