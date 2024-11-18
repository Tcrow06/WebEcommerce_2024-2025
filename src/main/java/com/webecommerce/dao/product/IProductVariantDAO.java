package com.webecommerce.dao.product;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

import java.util.List;

public interface IProductVariantDAO extends GenericDAO <ProductVariantEntity> {
    ProductVariantEntity getProductVariantByProduct (ProductEntity productEntity);

    ProductVariantEntity getProductVariantByColorAndSize (Long productId, String color, String size);

    List<ProductVariantEntity> getProductVariantsByProduct(ProductEntity productEntity);
}
