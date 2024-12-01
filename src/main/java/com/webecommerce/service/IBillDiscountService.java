package com.webecommerce.service;

import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IBillDiscountService {
    BillDiscountDTO save(BillDiscountDTO discount);

    List<BillDiscountDTO> findAll();

    List<BillDiscountDTO> getAllDiscountEligible(Long idUser);

    List<BillDiscountDTO> findAllOutStanding ();

    BillDiscountDTO findBillDiscountByCode(String code);

    BillDiscountDTO findBillDiscountByCodeAndValid(String code);


    List <BillDiscountDTO> findBillDiscountUpComming () ;

    List <BillDiscountDTO> findExpiredBillDiscount () ;

    List <BillDiscountDTO> findBillDiscountValid () ;

    BillDiscountDTO findById(Long id);

    List<BillDiscountDTO> getAllBillDiscount();

    List<BillDiscountDTO> findBillDiscountByPercent(String percent);

    List<BillDiscountDTO> findBillDiscountByTime(LocalDateTime inputTime);
}
