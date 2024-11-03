package com.webecommerce.dao.product;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

public interface IProductVariantDAO extends GenericDAO <ProductVariantEntity> {
    ProductVariantEntity getProductVariantByProduct (ProductEntity productEntity);
}
