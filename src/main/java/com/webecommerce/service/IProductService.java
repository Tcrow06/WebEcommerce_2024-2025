package com.webecommerce.service;

import com.webecommerce.entity.product.ProductEntity;

import java.util.List;

public interface IProductService {
    List<ProductEntity> getAllProducts();
}
