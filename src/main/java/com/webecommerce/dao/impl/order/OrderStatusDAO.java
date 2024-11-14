package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.entity.order.OrderStatusEntity;
import java.util.List;
import java.util.logging.Level;

public class OrderStatusDAO extends AbstractDAO<OrderStatusEntity> implements IOrderStatusDAO {
    public OrderStatusDAO() {
        super(OrderStatusEntity.class);
    }
    public List<OrderStatusEntity> getDeliveredOrdersByCustomerId(Long idUser) {
        String query = "SELECT o FROM OrderStatusEntity o "+
                "WHERE o.status = 'xong' AND o.order.customer.id = :idUser";

        try {
            return entityManager.createQuery(query, OrderStatusEntity.class)
                    .setParameter("idUser", idUser)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy đơn hàng đã giao thành công của khách hàng với ID: " + idUser, e);
            return null;
        }
    }
}
