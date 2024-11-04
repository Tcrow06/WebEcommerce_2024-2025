package com.webecommerce.service;

import com.webecommerce.dto.ProductVariantDTO;

public interface IProductVariantService {

    ProductVariantDTO getProductVariantById(Long id);
}
