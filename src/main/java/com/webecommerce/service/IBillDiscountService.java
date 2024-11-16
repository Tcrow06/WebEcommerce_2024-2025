package com.webecommerce.service;

import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;

import java.util.List;

public interface IBillDiscountService {
    BillDiscountDTO save(BillDiscountDTO discount);
    List<BillDiscountDTO> findAll();
    List<BillDiscountDTO> findAllOutStanding ();

    BillDiscountDTO findBillDiscountByCode(String code);

    BillDiscountDTO findBillDiscountByCodeAndValid(String code);




}
