package com.webecommerce.dao.product;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

import java.util.List;

public interface IProductDAO extends GenericDAO <ProductEntity> {
    List<ProductEntity> findProductsByCategoryCode(String categoryCode);
}
