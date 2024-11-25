package com.webecommerce.dao.impl.order;


import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderInfoDAO;
import com.webecommerce.entity.order.OrderInfoEntity;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;

public class OrderInfoDAO extends AbstractDAO<OrderInfoEntity> implements IOrderInfoDAO {

    public OrderInfoDAO() {
        super(OrderInfoEntity.class);
    }

    @Override
    public List<OrderInfoEntity> findOrderInfoByCustomerId(Long customerId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            String query = "SELECT od FROM OrderInfoEntity od WHERE od.customer.id = :customerId";
            List<OrderInfoEntity> orderInfos = entityManager.createQuery(query, OrderInfoEntity.class)
                    .setParameter("customerId", customerId)
                    .getResultList();

            entityManager.flush();
            entityManager.clear();

            transaction.commit();
            return orderInfos;
        } catch (NoResultException ex) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public boolean setOrderInfoDefault(OrderInfoEntity orderInfo) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin(); // Bắt đầu transaction

            // Cập nhật tất cả địa chỉ của khách hàng thành không phải mặc định
            Query queryZ = entityManager.createQuery("UPDATE OrderInfoEntity od SET od.isDefault = 0 WHERE od.customer.id = :customerId");
            queryZ.setParameter("customerId", orderInfo.getCustomer().getId());
            queryZ.executeUpdate();

            // Cập nhật địa chỉ được chọn thành mặc định
            Query queryO = entityManager.createQuery("UPDATE OrderInfoEntity od SET od.isDefault = 1 WHERE od.id = :selectedOrderInfoId");
            queryO.setParameter("selectedOrderInfoId", orderInfo.getId());
            queryO.executeUpdate();

            entityManager.flush();
            entityManager.clear();
            transaction.commit();
            return true;
        } catch (NoResultException ex) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback transaction nếu có lỗi
            }
            return false;
        }
    }


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
