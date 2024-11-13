package com.webecommerce.service.impl;

import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;
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

        ProductEntity productEntity = productDAO.findById(productDiscount.getProduct().getId());

        if (productEntity != null) {
            // Thiết lập liên kết giữa sản phẩm và discount
            productDiscountEntity.setProduct(productEntity);
            productEntity.setProductDiscount(productDiscountEntity); // Cập nhật liên kết hai chiều

            productEntity = productDAO.update(productEntity); // Cập nhật sản phẩm

            return productDiscountMapper.toDTO(productEntity.getProductDiscount());
        }

        return null;
    }

    @Override
    public ProductDiscountDTO findById(Long id) {
        ProductDiscountEntity productDiscountEntity = productDiscountDAO.findById(id);

        return productDiscountMapper.toDTO(productDiscountEntity);
    }
}
