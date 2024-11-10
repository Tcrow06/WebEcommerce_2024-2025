package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.GenericMapper;

public class ProductVariantMapper implements GenericMapper <ProductVariantDTO, ProductVariantEntity> {

    @Override
    public ProductVariantDTO toDTO(ProductVariantEntity productVariantEntity) {
        if (productVariantEntity == null) {
            return null;
        }

        ProductVariantDTO dto = new ProductVariantDTO();
        dto.setId(productVariantEntity.getId()); // If you have an ID in your entity
        dto.setPrice(productVariantEntity.getPrice());
        dto.setStatus(productVariantEntity.getStatus());
        dto.setImageUrl(productVariantEntity.getImageUrl());
        dto.setColor(productVariantEntity.getColor());
        dto.setSize(productVariantEntity.getSize());
        dto.setQuantity(productVariantEntity.getQuantity());
        dto.setName(productVariantEntity.getProduct().getName()); // Field này cần khi load sản phẩm

        return dto;
    }

    @Override
    public ProductVariantEntity toEntity(ProductVariantDTO productVariantDTO) {
        if (productVariantDTO == null) {
            return null;
        }

        ProductVariantEntity entity = new ProductVariantEntity();
        entity.setId(productVariantDTO.getId()); // If you have an ID in your DTO
        entity.setPrice(productVariantDTO.getPrice());
        entity.setStatus(productVariantDTO.getStatus());
        entity.setImageUrl(productVariantDTO.getImageUrl());
        entity.setColor(productVariantDTO.getColor().trim().replaceAll("\\s+", " ").toUpperCase());
        entity.setSize(productVariantDTO.getSize().trim().replaceAll("\\s+", " ").toUpperCase());
        entity.setQuantity(productVariantDTO.getQuantity());


        return entity;
    }
}
