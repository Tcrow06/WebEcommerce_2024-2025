package com.webecommerce.dao.discount;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;

import java.util.List;

public interface IBillDiscountDAO extends GenericDAO<BillDiscountEntity> {
<<<<<<< HEAD
    List<BillDiscountEntity> getAllDiscountEligible(Long idUser);
=======
    List<BillDiscountEntity> getBillDiscountByOutStanding(boolean outstanding);
>>>>>>> ef76205ce7b20f08253bb0423e86c8b114e5c770
}
