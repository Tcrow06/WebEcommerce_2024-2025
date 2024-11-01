package com.webecommerce.service.impl;

import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import java.util.List;

public class ProductService implements IProductService {

    @Inject
    private IProductDAO productDAO;

    @Override
    public List<ProductEntity> getAllProducts() {
        return productDAO.findAll();
    }
}
