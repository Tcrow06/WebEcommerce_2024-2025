package com.webecommerce.service.impl;

import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.service.IProductVariantService;

import javax.inject.Inject;

public class ProductVariantService implements IProductVariantService {
    @Inject
    IProductVariantDAO productVariantDAO;

    @Inject
    GenericMapper <ProductVariantDTO, ProductVariantEntity> productVariantMapper;


    public ProductVariantDTO getProductVariantByColorAndSize (Long productId, String color, String size) {
        ProductVariantEntity productVariant = productVariantDAO.getProductVariantByColorAndSize(productId, color, size);
        ProductVariantDTO productVariantDTO = new ProductVariantDTO();

        if (productVariant == null) {
            productVariantDTO.setQuantity(0);
            productVariantDTO.setId(-1L);
        } else productVariantDTO = productVariantMapper.toDTO(productVariant);

        return productVariantDTO;
    }
}
