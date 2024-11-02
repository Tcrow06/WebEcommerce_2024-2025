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

    public ProductVariantEntity getProductVariantByProduct (ProductEntity productEntity) {
        String query = "SELECT e FROM " + ProductVariantEntity.class.getSimpleName() +
                " e WHERE e.price = (SELECT MIN(pv.price) FROM ProductVariantEntity pv WHERE pv.product = :product)";

        try {
            return super.entityManager.createQuery(query, ProductVariantEntity.class)
                    .setParameter("product", productEntity)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "Không tìm thấy biến thể sản phẩm nào", e);
            return null;
        } catch (NonUniqueResultException e) {
            LOGGER.log(Level.SEVERE, "Có nhiều hơn một biến thể sản phẩm có giá thấp nhất", e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy biến thể sản phẩm", e);
            return null;
        }
    }
}
