package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;

public class ProductDiscountMapper extends DiscountMapper implements GenericMapper<ProductDiscountDTO, ProductDiscountEntity> {

    @Inject
    GenericMapper<ProductDTO, ProductEntity> productMapper;

    @Override
    public ProductDiscountDTO toDTO(ProductDiscountEntity productDiscountEntity) {
        ProductDiscountDTO productDiscount = new ProductDiscountDTO();

        if (super.toDTO(productDiscountEntity,productDiscount) == null)
            return null;

        productDiscount.setProduct(productMapper.toDTO(productDiscountEntity.getProduct()));

        return productDiscount;
    }

    @Override
    public ProductDiscountEntity toEntity(ProductDiscountDTO productDiscountDTO) {
        ProductDiscountEntity productDiscountEntity = new ProductDiscountEntity();

        if (super.toEntity(productDiscountDTO,productDiscountEntity) == null)
            return null;

        productDiscountEntity.getProduct().setId(productDiscountDTO.getProduct().getId());

        return productDiscountEntity;
    }
}
