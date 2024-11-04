package com.webecommerce.service.impl;

import com.webecommerce.dao.impl.product.ProductVariantDAO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.Impl.ProductVariantMapper;
import com.webecommerce.service.IProductVariantService;

import javax.inject.Inject;

public class ProductVariantService implements IProductVariantService {

    @Inject
    private ProductVariantDAO productVariantDAO;

    @Inject
    private ProductVariantMapper productVariantMapper;

    @Override
    public ProductVariantDTO getProductVariantById(Long id) {
        ProductVariantEntity productVariantEntity = productVariantDAO.getProductVariantById(id);
        return productVariantMapper.toDTO(productVariantEntity);
    }
}
