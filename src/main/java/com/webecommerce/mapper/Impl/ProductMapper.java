package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.CategoryEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.GenericMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ProductMapper implements GenericMapper <ProductDTO, ProductEntity> {
    ProductVariantMapper productVariantMapper = new ProductVariantMapper();

    @Override
    public ProductDTO toDTO(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId()); // If you have an ID in your entity
        dto.setName(entity.getName());
        dto.setHighlight(entity.isHighlight());
        dto.setStatus(entity.getStatus());

        CategoryDTO category = new CategoryDTO();
        category.setId(entity.getCategory().getId());
        dto.setCategory(category);

        if (entity.getIsNew() != null && ChronoUnit.DAYS.between(entity.getIsNew(), LocalDateTime.now()) > 7) {
            dto.setNew(false);
        } else {
            dto.setNew(true);
        }


        dto.setIsNewProduct(entity.getIsNew());

        dto.setBrand(entity.getBrand());
        dto.setDescription(entity.getDescription());
        dto.setSizeConversionTableUrl(entity.getSizeConversionTableUrl());

        return dto;
    }

    @Override
    public ProductEntity toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId()); // If you have an ID in your DTO
        entity.setName(dto.getName());
        entity.setHighlight(dto.isHighlight());
        entity.setStatus(dto.getStatus());
//        entity.setIsNew(dto.getIsNewProduct());
        entity.setBrand(dto.getBrand());
        entity.setDescription(dto.getDescription());

        CategoryEntity category = new CategoryEntity();
        category.setId(dto.getId());
        entity.setCategory(category); // Assuming CategoryEntity is already set
        entity.setSizeConversionTableUrl(dto.getSizeConversionTableUrl());

        // Convert ProductVariantDTOs to ProductVariantEntities
        List<ProductVariantEntity> productVariants =
                productVariantMapper.toEntityList(dto.getProductVariants());

        entity.setProductVariants(productVariants);

        return entity;
    }
}
