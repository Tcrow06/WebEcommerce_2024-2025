package com.webecommerce.service;

import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.entity.product.ProductVariantEntity;

public interface IProductVariantService {
    ProductVariantDTO getProductVariantByColorAndSize (Long productId, String color, String size);
}
