package com.webecommerce.service;

import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.paging.PageRequest;
import com.webecommerce.paging.Pageable;

import java.util.List;

public interface IProductVariantService {

    ProductVariantDTO getProductVariantByColorAndSize(Long productId, String color, String size);

    ProductVariantDTO findById(Long id);

}

