package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;

public class BillDiscountDAODAO extends AbstractDAO<BillDiscountEntity> implements IBillDiscountDAO {

    public BillDiscountDAODAO() {
        super(BillDiscountEntity.class);
    }
}
