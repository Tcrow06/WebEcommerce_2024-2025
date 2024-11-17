package com.webecommerce.dao.impl.discount;

import com.webecommerce.context.DBContext;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.DiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public List<ProductDiscountEntity> findDiscounNotProduct() {
//        String query = "SELECT e FROM " + ProductDiscountEntity.class.getSimpleName() +
//                " e WHERE e.startDate >= :startDate and e.endDate <= :endDate";
//
//        try {
//            return entityManager.createQuery(query, ProductDiscountEntity.class)
//                    .setParameter("startDate", startDate)
//                    .setParameter("endDate", endDate)
//                    .getResultList();
//        } catch (NoResultException e) {
//            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể giảm gía nào", e);
//            return null;
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể giảm giá", e);
//            return null;
//        }
        return super.findByAttribute("product",null);
    }


    /**
     * Tìm discount theo ngày bắt đầu (ngày bắt đầu phải lớn hơn ngày bắt đầu truyền vào -> đã xảy ra)
     * @return discount
     */
    public List<ProductDiscountEntity> findDiscounthaveProductByDate(LocalDateTime start) {
        String query = "SELECT d FROM ProductDiscountEntity d " +
                "JOIN d.product p " +
                "WHERE p IS NOT NULL and d.startDate >= :start and d.endDate >= :start"; ;

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
}