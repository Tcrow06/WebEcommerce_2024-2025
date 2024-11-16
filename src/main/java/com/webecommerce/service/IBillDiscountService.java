package com.webecommerce.service;

import com.webecommerce.dto.discount.BillDiscountDTO;

import java.util.List;

public interface IBillDiscountService {
    BillDiscountDTO save(BillDiscountDTO discount);

    List<BillDiscountDTO> findAll();

    List<BillDiscountDTO> getAllDiscountEligible(Long idUser);

    List<BillDiscountDTO> findAllOutStanding ();

    List <BillDiscountDTO> findBillDiscountUpComming () ;

    List <BillDiscountDTO> findExpiredBillDiscount () ;

    List <BillDiscountDTO> findBillDiscountValid () ;

    BillDiscountDTO findById(Long id);
}
