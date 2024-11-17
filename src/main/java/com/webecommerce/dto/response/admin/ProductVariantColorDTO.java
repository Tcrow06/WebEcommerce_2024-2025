package com.webecommerce.dto.response.admin;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dto.BaseDTO;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductVariantColorDTO extends BaseDTO<ProductVariantColorDTO> {
    private Long id;

    private EnumProductStatus status;

    private String imageUrl;

    private String color;

    private List <SizeVariantDTO> sizes;

    public List<SizeVariantDTO> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeVariantDTO> sizes) {
        this.sizes = sizes;
    }

    private Part image;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public EnumProductStatus getStatus() {
        return status;
    }

    public ProductVariantColorDTO(String color,String imageUrl) {
        this.color = color;
        this.imageUrl = imageUrl;
        this.sizes = new ArrayList<>();
    }

    public void setStatus(EnumProductStatus status) {
        this.status = status;
    }

    public void setStatus (String status) {
        this.status = EnumProductStatus.valueOf(status);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
}