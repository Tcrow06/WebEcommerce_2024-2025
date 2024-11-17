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

    public List<ProductDiscountEntity> findDiscountByDate(LocalDateTime startDate, LocalDateTime endDate) {
        String query = "SELECT e FROM " + ProductDiscountEntity.class.getSimpleName() +
                " e WHERE e.startDate >= :startDate and e.endDate <= :endDate";

        try {
            return entityManager.createQuery(query, ProductDiscountEntity.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm gía nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        }
    }

    public List<ProductDiscountEntity> findDiscountByDate(LocalDateTime date) {
        String query = "SELECT e FROM " + ProductDiscountEntity.class.getSimpleName() +
                " e WHERE e.startDate <= :date and e.endDate >= :date";

        try {
            return entityManager.createQuery(query, ProductDiscountEntity.class)
                    .setParameter("date", date)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm gía nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
            return null;
        }
    }
}