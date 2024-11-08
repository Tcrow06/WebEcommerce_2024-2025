package com.webecommerce.service;

import com.webecommerce.dto.ProductDTO;
import com.webecommerce.entity.product.ProductEntity;

import java.util.List;

public interface IProductService {
    List<String> getBrands();
    List<ProductDTO> findProductsByBrand(String brand);
    List<ProductDTO> findAll ();
    ProductDTO save(ProductDTO product);
    List<ProductDTO> findProductsByCategoryCode(String categoryCode);
    ProductDTO getProductById(Long id);
    List<String> getListColorBySize (String size, Long productId);
    List<String> getListSizeByColor (String color, Long productId);

    List<ProductDTO> findProductOnSale(int limit);
    List<ProductDTO> findProductIsNew(int limit);
    List<ProductDTO> findProductOther(int limit);

    List<ProductDTO> findProductForAllTag(int limit);
}
