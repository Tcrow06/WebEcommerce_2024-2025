package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;
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
        dto.setNew(entity.isNew());
        dto.setBrand(entity.getBrand());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory()); // Assuming CategoryEntity is already set

        // Convert ProductVariantEntities to ProductVariantDTOs
        List<ProductVariantDTO> productVariants =
            productVariantMapper.toDTOList(entity.getProductVariants());

        dto.setProductVariants(productVariants);

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
        entity.setNew(dto.isNew());
        entity.setBrand(dto.getBrand());
        entity.setDescription(dto.getDescription());
        entity.setCategory(dto.getCategory()); // Assuming CategoryEntity is already set

        // Convert ProductVariantDTOs to ProductVariantEntities
        List<ProductVariantEntity> productVariants =
                productVariantMapper.toEntityList(dto.getProductVariants());

        entity.setProductVariants(productVariants);

        return entity;
    }
}
