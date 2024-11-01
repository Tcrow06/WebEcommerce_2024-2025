package com.webecommerce.dao.impl.product;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.entity.product.ProductVariantEntity;

public class ProductVariantDAO extends AbstractDAO <ProductVariantEntity> implements IProductVariantDAO {
    public ProductVariantDAO() {
        super(ProductVariantEntity.class);
    }
}
