package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;

import java.util.List;

public class BillDiscountDAO extends AbstractDAO<BillDiscountEntity> implements IBillDiscountDAO {

    public BillDiscountDAO() {
        super(BillDiscountEntity.class);
    }

    public List<BillDiscountEntity> getBillDiscountByOutStanding(boolean outstanding) {
        return super.findByAttribute("isOutStanding", outstanding);
    }

}
