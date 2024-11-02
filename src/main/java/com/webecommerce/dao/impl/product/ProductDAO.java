package com.webecommerce.dao.impl.product;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

public class ProductDAO extends AbstractDAO<ProductEntity> implements IProductDAO {

    public ProductDAO() {
        super(ProductEntity.class);
    }
}
