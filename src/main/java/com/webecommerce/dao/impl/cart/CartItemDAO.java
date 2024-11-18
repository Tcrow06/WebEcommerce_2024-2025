package com.webecommerce.dao.impl.cart;


import com.webecommerce.dao.cart.ICartItemDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.entity.order.OrderInfoEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CartItemDAO extends AbstractDAO<CartItemEntity> implements ICartItemDAO {

    public CartItemDAO() {
        super(CartItemEntity.class);
    }

    // Xóa cart item thông qua id sản phẩm
    @Override
    public void deleteByProductVariantId(Long productVariantId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Sử dụng Query thay vì TypedQuery
            String query = "DELETE FROM CartItemEntity ci WHERE ci.productVariant.id = :productVariantId";
            Query deleteQuery = entityManager.createQuery(query);
            deleteQuery.setParameter("productVariantId", productVariantId);

            // Thực thi câu lệnh DELETE
            deleteQuery.executeUpdate();

            entityManager.flush();
            entityManager.clear();

            transaction.commit();
        } catch (NoResultException ex) {
            transaction.rollback();
        }
    }
}
