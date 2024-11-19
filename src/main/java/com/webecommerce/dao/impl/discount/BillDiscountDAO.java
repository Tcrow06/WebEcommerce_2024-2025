package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

public class BillDiscountDAO extends AbstractDAO<BillDiscountEntity> implements IBillDiscountDAO {

    public BillDiscountDAO() {
        super(BillDiscountEntity.class);
    }


    public List<BillDiscountEntity> getAllDiscountEligible(Long idUser) {
        String query = "SELECT b FROM BillDiscountEntity b JOIN CustomerEntity c " +
                "ON b.loyaltyPointsRequired <= c.loyaltyPoint " +
                "WHERE c.id = :idUser " +
                "AND CURRENT_TIMESTAMP BETWEEN b.startDate AND b.endDate";
        EntityManager entityManager = HibernateUtil.getEmFactory().createEntityManager();
        try {

            List<BillDiscountEntity> result = entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("idUser", idUser)
                    .getResultList();

            // Kiểm tra nếu kết quả trả về không có mã giảm giá
            if (result.isEmpty()) {
                LOGGER.log(Level.INFO, "Không có mã giảm giá phù hợp cho khách hàng ID: " + idUser);
            }


            return result;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy bill có loyaltyPointsRequired < loyaltyPoint của khách hàng với ID: " + idUser, e);
            return null;
        }finally {
            entityManager.close();
        }
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

    public List <BillDiscountEntity> findBillDiscountOutStandingAndStillValid () {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.endDate >= :now and b.isOutStanding = :isOutStanding"; ;

        try {
            return entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("now", LocalDateTime.now())
                    .setParameter("isOutStanding", true)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        }
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
