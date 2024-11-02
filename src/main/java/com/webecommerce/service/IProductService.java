package com.webecommerce.service;

import com.webecommerce.dto.ProductDTO;
import com.webecommerce.entity.product.ProductEntity;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll ();
    ProductDTO save(ProductDTO product);
    List<ProductDTO> findProductsByCategoryCode(String categoryCode);
    public ProductDTO getProductById(Long id);
}
