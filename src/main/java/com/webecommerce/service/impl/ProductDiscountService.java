package com.webecommerce.service.impl;

import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.service.IProductDiscountService;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class ProductDiscountService implements IProductDiscountService {
    @Inject
    IProductDiscountDAO productDiscountDAO;

    @Inject
    IProductDAO productDAO;

    @Inject
    GenericMapper <ProductDiscountDTO, ProductDiscountEntity> productDiscountMapper;


    @Transactional
    public ProductDiscountDTO save (ProductDiscountDTO productDiscount) {

        ProductDiscountEntity productDiscountEntity = productDiscountMapper.toEntity(productDiscount);

        productDiscountEntity.setProduct(
            productDAO.findById(productDiscount.getProduct().getId())
        );

        return productDiscountMapper.toDTO(productDiscountEntity);
    }
}
