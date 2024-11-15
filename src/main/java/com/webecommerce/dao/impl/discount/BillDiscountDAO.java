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

    public List <BillDiscountEntity> findBillDiscountValid () {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.startDate <= :start and b.endDate >= :start"; ;

        try {
            return entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("start", LocalDateTime.now())
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        }
    }

    public List <BillDiscountEntity> findBillDiscountUpComming () {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.startDate >= :start and b.endDate >= :start"; ;

        try {
            return entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("start", LocalDateTime.now())
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        }
    }

    public List <BillDiscountEntity> findExpiredBillDiscount () {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.endDate <= :start"; ;

        try {
            return entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("start", LocalDateTime.now())
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        }
    }


}
