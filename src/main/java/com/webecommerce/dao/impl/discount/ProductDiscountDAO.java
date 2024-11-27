package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

public class ProductDiscountDAO extends AbstractDAO<ProductDiscountEntity> implements IProductDiscountDAO {

    public ProductDiscountDAO() {
        super(ProductDiscountEntity.class);
    }

    public List<ProductDiscountEntity> findDiscounNotProduct() {
        return super.findByAttribute("product",null);
    }
    public List<ProductDiscountEntity> findProductDiscountByProductName(String productName) {
        String query = "SELECT pd FROM ProductDiscountEntity pd " +
                "JOIN pd.product p " +
                "WHERE p.name = :productName";

        try {
            return entityManager.createQuery(query, ProductDiscountEntity.class)
                    .setParameter("productName", productName)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy mã giảm giá cho sản phẩm với tên: " + productName, e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm mã giảm giá", e);
            return null;
        }
    }
    public List<ProductDiscountEntity> getAllProductDiscount() {
        String query = "SELECT d FROM ProductDiscountEntity d " +
                "JOIN d.product p " +
                "WHERE p IS NOT NULL AND d.endDate > CURRENT_TIMESTAMP";

        try {
            return entityManager.createQuery(query, ProductDiscountEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy mã giảm giá nào có sản phẩm liên kết", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách mã giảm giá", e);
            return null;
        }
    }
    public List<ProductDiscountEntity> findProductDiscountByTime(LocalDateTime inputTime) {
        String query = "SELECT pd FROM ProductDiscountEntity pd " +
                "WHERE :inputTime BETWEEN pd.startDate AND pd.endDate";

        try {
            // Thực hiện truy vấn và trả về kết quả
            return entityManager.createQuery(query, ProductDiscountEntity.class)
                    .setParameter("inputTime", inputTime)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy mã giảm giá sản phẩm có thời gian nằm trong phạm vi.", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm mã giảm giá sản phẩm theo thời gian", e);
            return null;
        }
    }
    public List<ProductDiscountEntity> findDiscounthaveProductByDate(LocalDateTime start) {
        EntityManager em = super.getEntityManager();


        String query = "SELECT d FROM ProductDiscountEntity d " +
                "JOIN d.product p " +
                "WHERE p IS NOT NULL and d.startDate >= :start and d.endDate >= :start";

        try {
            return em.createQuery(query, ProductDiscountEntity.class)
                    .setParameter("start", start)
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
    public List<ProductDiscountEntity> findDiscounthaveProductByDate() {

        EntityManager em = super.getEntityManager();

        String query = "SELECT d FROM ProductDiscountEntity d " +
                "JOIN d.product p " +
                "WHERE p IS NOT NULL and d.startDate <= :start and d.endDate >= :start"; ;

        try {
            return em.createQuery(query, ProductDiscountEntity.class)
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
    public List<ProductDiscountEntity> findProductDiscountByPercent(String percent) {
        String query = "SELECT pd FROM ProductDiscountEntity pd " +
                "WHERE pd.discountPercentage >= :percent";

        try {
            return entityManager.createQuery(query, ProductDiscountEntity.class)
                    .setParameter("percent", Integer.parseInt(percent))
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy mã giảm giá cho phần trăm giảm giá: " + percent, e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm mã giảm giá sản phẩm theo phần trăm", e);
            return null;
        }
    }
    @Override
    public int countDiscountValid(){
        String query ="SELECT count(d) FROM ProductDiscountEntity d " +
                "WHERE d.startDate <= :current_date " +
                "AND d.endDate >= :current_date";
        try {
            Long count = entityManager.createQuery(query, Long.class)
                    .setParameter("current_date",LocalDateTime.now())
                    .getSingleResult();
            return  count == null ? 0 : count.intValue();

        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return 0;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return 0;
        }
    }
}