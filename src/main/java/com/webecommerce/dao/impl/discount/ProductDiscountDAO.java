package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.ProductDiscountEntity;

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


    /**
     * Tìm discount theo ngày bắt đầu (ngày bắt đầu phải lớn hơn ngày bắt đầu truyền vào -> đã xảy ra)
     * @return discount
     */
    public List<ProductDiscountEntity> findDiscounthaveProductByDate(LocalDateTime start) {
        String query = "SELECT d FROM ProductDiscountEntity d " +
                "JOIN d.product p " +
                "WHERE p IS NOT NULL and d.startDate >= :start and d.endDate >= :start";

        try {
            return entityManager.createQuery(query, ProductDiscountEntity.class)
                    .setParameter("start", start)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm giá nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        }
    }


    /**
     * Tìm discount theo ngày bắt đầu (ngày bắt đầu phải nhỏ hơn ngày bắt đầu truyền vào -> chưa xảy ra)
     * @return discount
     */
    public List<ProductDiscountEntity> findDiscounthaveProductByDate() {
        String query = "SELECT d FROM ProductDiscountEntity d " +
                "JOIN d.product p " +
                "WHERE p IS NOT NULL and d.startDate <= :start and d.endDate >= :start"; ;

        try {
            return entityManager.createQuery(query, ProductDiscountEntity.class)
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