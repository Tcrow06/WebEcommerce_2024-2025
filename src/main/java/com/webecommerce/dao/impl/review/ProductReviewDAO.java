package com.webecommerce.dao.impl.review;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.review.IProductReviewDAO;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.review.ProductReviewEntity;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.logging.Level;

public class ProductReviewDAO extends AbstractDAO<ProductReviewEntity> implements IProductReviewDAO {
    public ProductReviewDAO() {
        super(ProductReviewEntity.class);
    }

    @Override
    public int calculateStarByProduct(Long productId) {
        String query = "SELECT AVG(d.numberOfStars) FROM ProductReviewEntity d " +
                "JOIN d.orderDetail od JOIN od.productVariant pv " +
                "WHERE pv.product.id = :productId";

        Double averageStars = (Double) entityManager.createQuery(query)
                .setParameter("productId", productId)
                .getSingleResult();

        return averageStars != null ? (int) Math.round(averageStars) : 0;
    }

    @Override
    public int countProductReviewByProduct(Long productId) {
        String query = "SELECT count(*) FROM ProductReviewEntity d " +
                "JOIN d.orderDetail od JOIN od.productVariant pv " +
                "WHERE pv.product.id = :productId";

        Long reviewCount = (Long) entityManager.createQuery(query)
                .setParameter("productId", productId)
                .getSingleResult();

        return reviewCount != null ? reviewCount.intValue() : 0;
    }


    public List<ProductReviewEntity> getProductReviewByProduct (Long productId) {
        String query = "SELECT d FROM ProductReviewEntity d " +
                "JOIN d.orderDetail od JOIN od.productVariant pv  " +
                "WHERE pv.product.id = :productId";

        try {
            return entityManager.createQuery(query, ProductReviewEntity.class)
                    .setParameter("productId", productId)
                    .getResultList();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy review nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy review", e);
            return null;
        }
    }

    public ProductReviewEntity getProductReviewByOrderDetailId (Long OrderDetailId) {
        String query = "SELECT d FROM ProductReviewEntity d " +
                "JOIN d.orderDetail od " +
                "WHERE od.id = :orderDetailId";

        try {
            return entityManager.createQuery(query, ProductReviewEntity.class)
                    .setParameter("orderDetailId", OrderDetailId)
                    .setMaxResults(1) // Giới hạn kết quả về 1
                    .getSingleResult(); // Lấy kết quả duy nhất
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy review nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy review", e);
            return null;
        }
    }

}
