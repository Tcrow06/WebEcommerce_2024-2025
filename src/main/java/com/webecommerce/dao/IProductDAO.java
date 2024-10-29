package com.webecommerce.dao;

import com.webecommerce.entity.product.ProductEntity;

import java.util.List;

public interface IProductDAO {

    // Get all Product
     List<ProductEntity> getAllProduct();
}
