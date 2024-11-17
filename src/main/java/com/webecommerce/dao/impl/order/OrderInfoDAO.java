package com.webecommerce.dao.impl.order;


import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderInfoDAO;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.entity.product.ProductEntity;

import java.util.List;
import java.util.logging.Level;

public class OrderInfoDAO extends AbstractDAO<OrderInfoEntity> implements IOrderInfoDAO {

    public OrderInfoDAO() {
        super(OrderInfoEntity.class);
    }

    @Override
    public OrderInfoEntity findDefaultOrderInfoByUserId(Long idUser) {
        String query = "SELECT u FROM OrderInfoEntity u WHERE u.customer.id = :userId AND u.isDefault = 1";
        try {
            return entityManager.createQuery(query, OrderInfoEntity.class)
                    .setParameter("userId", idUser)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy địa chỉ theo idUser: " + idUser, e);
            return null;
        }
    }
}
