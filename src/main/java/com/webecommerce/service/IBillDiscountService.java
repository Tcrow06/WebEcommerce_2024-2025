package com.webecommerce.service;

import com.webecommerce.dto.discount.BillDiscountDTO;

import java.util.List;

public interface IBillDiscountService {
    BillDiscountDTO save(BillDiscountDTO discount);
    List<BillDiscountDTO> findAll();
}
