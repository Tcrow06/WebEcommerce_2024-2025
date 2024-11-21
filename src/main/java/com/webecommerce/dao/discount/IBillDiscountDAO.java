package com.webecommerce.dao.discount;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;

import java.util.List;

public interface IBillDiscountDAO extends GenericDAO<BillDiscountEntity> {
    List<BillDiscountEntity> getAllDiscountEligible(Long idUser);

    List<BillDiscountEntity> getBillDiscountByOutStanding(boolean outstanding);

    BillDiscountEntity findBillDiscountByCode(String code);

    BillDiscountEntity findBillDiscountByCodeAndValid(String code);

    List <BillDiscountEntity> findBillDiscountOutStandingAndStillValid ();


    List <BillDiscountEntity> findBillDiscountUpComming ();

    List <BillDiscountEntity> findExpiredBillDiscount ();

    List <BillDiscountEntity> findBillDiscountValid ();
    int countDiscountValid();
}
