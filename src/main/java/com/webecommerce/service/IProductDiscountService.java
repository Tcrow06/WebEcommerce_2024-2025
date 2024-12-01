package com.webecommerce.service;


import com.webecommerce.dto.discount.DiscountDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductDiscountService {

    ProductDiscountDTO save (ProductDiscountDTO discount);

    ProductDiscountDTO update(ProductDiscountDTO productDiscount);

    ProductDiscountDTO findById(Long id);

    ProductDiscountDTO cancelProductDiscount(Long id);

    List<ProductDiscountDTO> getProductDiscountValid();

    List <ProductDiscountDTO> getUpcommingProductDiscount ();

    ProductDiscountDTO findByIdAndHaveProduct(Long id);

    List<ProductDiscountDTO> getAllProductDiscount();

    List<ProductDiscountDTO> findProductDiscountByProductName(String productName);

    List<ProductDiscountDTO> findProductDiscountByPercent(String percent);

    List<ProductDiscountDTO> findProductDiscountByTime(LocalDateTime inputTime);
}
