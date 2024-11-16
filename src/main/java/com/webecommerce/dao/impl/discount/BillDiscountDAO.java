package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

public class BillDiscountDAO extends AbstractDAO<BillDiscountEntity> implements IBillDiscountDAO {

    public BillDiscountDAO() {
        super(BillDiscountEntity.class);
    }

    public List<BillDiscountEntity> getBillDiscountByOutStanding(boolean outstanding) {
        return super.findByAttribute("isOutStanding", outstanding);
    }

    @Override
    public BillDiscountEntity findBillDiscountByCode(String code) {
        List<BillDiscountEntity> list = findByAttribute("code", code);
        return list.isEmpty()? null : list.get(0);
    }
    @Override
    public BillDiscountEntity findBillDiscountByCodeAndValid(String code) {
        String query = "SELECT e FROM " + BillDiscountEntity.class.getSimpleName() +
                " e WHERE e.startDate <= :date and e.endDate >= :date and e.code = :code";

        try {
            return entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("date", LocalDateTime.now())
                    .setParameter("code",code)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm gía nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        }
    }

}
