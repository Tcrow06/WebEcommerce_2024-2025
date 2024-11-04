package com.webecommerce.service;

import com.webecommerce.dto.ProductVariantDTO;

public interface IProductVariantService {

    ProductVariantDTO getProductVariantByColorAndSize(Long productId, String color, String size);

    ProductVariantDTO findById(Long id);
}

