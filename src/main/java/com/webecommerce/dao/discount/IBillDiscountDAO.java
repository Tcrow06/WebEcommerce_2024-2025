package com.webecommerce.dao.discount;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;

import java.util.List;

public interface IBillDiscountDAO extends GenericDAO<BillDiscountEntity> {
    List<BillDiscountEntity> getBillDiscountByOutStanding(boolean outstanding);

    BillDiscountEntity findBillDiscountByCode(String code);
    BillDiscountEntity findBillDiscountByCodeAndValid(String code);


}
