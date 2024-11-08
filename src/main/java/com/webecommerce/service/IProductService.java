package com.webecommerce.service;

import com.webecommerce.dto.ProductDTO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.paging.Pageable;

import java.util.List;

public interface IProductService {
    List<String> getBrands();
    List<ProductDTO> findProductsByBrand(String brand);
    List<ProductDTO> findAll (Pageable pageable);
    ProductDTO save(ProductDTO product);
    List<ProductDTO> findProductsByCategoryCode(String categoryCode);
    ProductDTO getProductById(Long id);
    List<String> getListColorBySize (String size, Long productId);
    List<String> getListSizeByColor (String color, Long productId);
    Long getTotalItem();
    int setTotalPage(Long totalItem, int maxPageItem);
}
