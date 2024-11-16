package com.webecommerce.dao.impl.discount;

import com.webecommerce.context.DBContext;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.DiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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