package com.webecommerce.service;

import com.webecommerce.dto.ProductDTO;
import com.webecommerce.entity.product.ProductEntity;

import java.util.List;

public interface IProductService {
    List<ProductEntity> getAllProducts();
    ProductDTO save(ProductDTO product);
}
