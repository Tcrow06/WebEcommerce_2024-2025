package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.review.ProductReviewDTO;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.review.ProductReviewEntity;
import com.webecommerce.mapper.GenericMapper;

public class ProductReviewMapper implements GenericMapper <ProductReviewDTO, ProductReviewEntity> {


    @Override
    public ProductReviewDTO toDTO(ProductReviewEntity entity) {
        if (entity == null) {
            return null;
        }

        ProductReviewDTO dto = new ProductReviewDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setDate(entity.getDate());
        dto.setNumberOfStars(entity.getNumberOfStars());

        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
            dto.setNameCustomer(entity.getCustomer().getName());
        }

        return dto;
    }

    @Override
    public ProductReviewEntity toEntity(ProductReviewDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductReviewEntity entity = new ProductReviewEntity();
        entity.setId(dto.getId()); // Assuming BaseDTO has an `id` field
        entity.setContent(dto.getContent());
        entity.setDate(dto.getDate());
        entity.setNumberOfStars(dto.getNumberOfStars());

        return entity;
    }
}
