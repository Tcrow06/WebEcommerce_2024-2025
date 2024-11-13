package com.webecommerce.service;


import com.webecommerce.dto.discount.DiscountDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;

public interface IProductDiscountService {
    ProductDiscountDTO save (ProductDiscountDTO discount);
    ProductDiscountDTO findById(Long id);
}
