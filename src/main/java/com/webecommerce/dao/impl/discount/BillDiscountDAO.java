package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<BillDiscountEntity> findBillDiscountByTime(LocalDateTime inputTime) {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE :inputTime BETWEEN b.startDate AND b.endDate";

        EntityManager entityManager = HibernateUtil.getEmFactory().createEntityManager();
        try {
            // Truyền thời gian đầu vào vào truy vấn
            List<BillDiscountEntity> result = entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("inputTime", inputTime)
                    .getResultList();

            if (result.isEmpty()) {
                LOGGER.log(Level.INFO, "Không có mã giảm giá nào có thời gian nằm giữa startDate và endDate.");
            }

            return result;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy mã giảm giá có thời gian nằm giữa startDate và endDate", e);
            return null;
        } finally {
            entityManager.close();
        }
    }

    public List<BillDiscountEntity> findBillDiscountByPercent(String percent) {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.discountPercentage > :percent " +
                "AND CURRENT_TIMESTAMP BETWEEN b.startDate AND b.endDate";

        EntityManager entityManager = HibernateUtil.getEmFactory().createEntityManager();
        try {
            int discountPercent = Integer.parseInt(percent);

            List<BillDiscountEntity> result = entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("percent", discountPercent)
                    .getResultList();

            if (result.isEmpty()) {
                LOGGER.log(Level.INFO, "Không có mã giảm giá có phần trăm lớn hơn " + percent);
            }

            return result;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy mã giảm giá có phần trăm lớn hơn: " + percent, e);
            return null;
        } finally {
            entityManager.close();
        }
    }


    public List<BillDiscountEntity> getAllBillDiscount() {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.endDate > CURRENT_TIMESTAMP";

        EntityManager entityManager = HibernateUtil.getEmFactory().createEntityManager();
        List<BillDiscountEntity> list = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            list = entityManager.createQuery(query, BillDiscountEntity.class)
                    .getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace(); // Log lỗi
        } finally {
            entityManager.close();
        }
        return list;
    }

    public boolean billDiscountCodeExists (String code) {
        return super.existsByAttribute("code", code);
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

        EntityManager em = super.getEntityManager();

        try {
            return em.createQuery(query, BillDiscountEntity.class)
                    .setParameter("now", LocalDateTime.now())
                    .setParameter("isOutStanding", true)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        } finally {
            super.closeEntityManager(em);
        }
    }


    public List <BillDiscountEntity> findBillDiscountValid () {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.startDate <= :start and b.endDate >= :start";

        EntityManager em = super.getEntityManager();

        try {
            return em.createQuery(query, BillDiscountEntity.class)
                    .setParameter("start", LocalDateTime.now())
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        } finally {
            super.closeEntityManager(em);
        }
    }




    public List <BillDiscountEntity> findBillDiscountUpComming () {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.startDate >= :start and b.endDate >= :start";

        EntityManager em = super.getEntityManager();

        try {
            return em.createQuery(query, BillDiscountEntity.class)
                    .setParameter("start", LocalDateTime.now())
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        } finally {
            super.closeEntityManager(em);
        }
    }

    public List <BillDiscountEntity> findExpiredBillDiscount () {
        String query = "SELECT b FROM BillDiscountEntity b " +
                "WHERE b.endDate <= :start";

        EntityManager em = super.getEntityManager();

        try {
            return em.createQuery(query, BillDiscountEntity.class)
                    .setParameter("start", LocalDateTime.now())
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        } finally {
            super.closeEntityManager(em);
        }
    }

    @Override
    public int countDiscountValid() {
        String jpql = "SELECT count(b) from BillDiscountEntity b " +
                "WHERE b.startDate <= :current_date " +
                "AND b.endDate >= :current_date";
        try {
            Long count = entityManager.createQuery(jpql,Long.class)
                    .setParameter("current_date", LocalDateTime.now())
                    .getSingleResult();
            return count == null ? 0 : count.intValue();
        }catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
        }
        return 0;
    }

}
