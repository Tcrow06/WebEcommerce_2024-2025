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
            List<OrderInfoEntity> results = entityManager.createQuery(query, OrderInfoEntity.class)
                    .setParameter("userId", idUser)
                    .getResultList();
            if (results.isEmpty()) {
                return null; // Không tìm thấy kết quả nào
            } else {
                return results.get(0); // Lấy kết quả đầu tiên nếu cần
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy địa chỉ theo idUser: " + idUser, e);
            return null;
        }
    }
}
