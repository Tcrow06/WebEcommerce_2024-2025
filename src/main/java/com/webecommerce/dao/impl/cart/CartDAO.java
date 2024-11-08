package com.webecommerce.dao.impl.cart;

import com.webecommerce.dao.cart.ICartDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.cart.CartEntity;

import javax.persistence.TypedQuery;

public class CartDAO extends AbstractDAO<CartEntity> implements ICartDAO {
    public CartDAO() {
        super(CartEntity.class);
    }

    @Override
    public CartEntity findByCustomerId(Long customerId) {
        String jpql = "SELECT c FROM CartEntity c WHERE c.customer.id = :customerId";
        TypedQuery<CartEntity> query = entityManager.createQuery(jpql, CartEntity.class);
        query.setParameter("customerId", customerId);

        return query.getResultStream().findFirst().orElse(null);
    }
}
