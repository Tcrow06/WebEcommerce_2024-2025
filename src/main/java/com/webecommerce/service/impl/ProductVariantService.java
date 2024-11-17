package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.Impl.ProductVariantMapper;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.service.IProductVariantService;

import javax.inject.Inject;

public class ProductVariantService implements IProductVariantService {

    @Inject
    private IProductVariantDAO productVariantDAO;

    @Inject
    private ProductVariantMapper productVariantMapper;

    @Override
    public ProductVariantDTO findById(Long id) {
        ProductVariantEntity productVariantEntity = productVariantDAO.findById(id);
        return productVariantMapper.toDTO(productVariantEntity);
    }


    public ProductVariantDTO getProductVariantByColorAndSize (Long productId, String color, String size) {
        ProductVariantEntity productVariant = productVariantDAO.getProductVariantByColorAndSize(productId, color, size);
        ProductVariantDTO productVariantDTO = new ProductVariantDTO();

        if (productVariant != null) {
            if (productVariant.getStatus() == EnumProductStatus.SELLING) {
                return productVariantMapper.toDTO(productVariant);
            }
        }

        productVariantDTO.setQuantity(0);
        productVariantDTO.setId(-1L);

        return productVariantDTO;
    }
}
