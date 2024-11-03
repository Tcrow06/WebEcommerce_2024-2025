package com.webecommerce.dao.impl.product;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.logging.Level;

public class ProductVariantDAO extends AbstractDAO <ProductVariantEntity> implements IProductVariantDAO {
    public ProductVariantDAO() {
        super(ProductVariantEntity.class);
    }

    public ProductVariantEntity getProductVariantByProduct(ProductEntity productEntity) {
        String query = "SELECT e FROM " + ProductVariantEntity.class.getSimpleName() +
                " e WHERE e.product = :product ORDER BY e.price ASC";

        try {
            return entityManager.createQuery(query, ProductVariantEntity.class)
                    .setParameter("product", productEntity)
                    .setMaxResults(1) // Giới hạn kết quả về 1
                    .getSingleResult(); // Lấy kết quả duy nhất
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể sản phẩm nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể sản phẩm", e);
            return null;
        }
    }

    public ProductVariantEntity getProductVariantByColorAndSize (Long productId, String color, String size) {
        String query = "SELECT e FROM " + ProductVariantEntity.class.getSimpleName() +
                " e WHERE e.product.id = :productId and e.color = :color and e.size = :size";

        try {
            return entityManager.createQuery(query, ProductVariantEntity.class)
                    .setParameter("productId", productId)
                    .setParameter("color", color)
                    .setParameter("size", size)
                    .setMaxResults(1) // Giới hạn kết quả về 1
                    .getSingleResult(); // Lấy kết quả duy nhất
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể sản phẩm nào", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể sản phẩm", e);
            return null;
        }
    }

}
