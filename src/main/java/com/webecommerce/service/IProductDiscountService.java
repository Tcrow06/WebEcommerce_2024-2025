package com.webecommerce.service;


import com.webecommerce.dto.discount.DiscountDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;

import java.util.List;

public interface IProductDiscountService {

    ProductDiscountDTO save (ProductDiscountDTO discount);

    ProductDiscountDTO cancelProductDiscount(Long id);

    List<ProductDiscountDTO> getProductDiscountValid();

    List <ProductDiscountDTO> getExpiredProductDiscount ();

    List <ProductDiscountDTO> getCommingProductDiscount ();
}
