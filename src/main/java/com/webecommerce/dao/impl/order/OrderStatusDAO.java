package com.webecommerce.dao.impl.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.dto.notinentity.TransferListOderStatusDTO;
import com.webecommerce.entity.order.OrderStatusEntity;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.*;

public class OrderStatusDAO extends AbstractDAO<OrderStatusEntity> implements IOrderStatusDAO {
    public OrderStatusDAO() {
        super(OrderStatusEntity.class);
    }
    @Override
    public List<TransferListOderStatusDTO> getStatusOrders(Long idUser, EnumOrderStatus status) {
        String query = "SELECT o.id, o.date, od.productVariant.imageUrl " +
                "FROM OrderStatusEntity o " +
                "JOIN OrderDetailEntity od ON o.order.id = od.order.id " +
                "WHERE o.status = :status AND o.order.customer.id = :idUser";

        List<Object[]> result = entityManager.createQuery(query, Object[].class)
                .setParameter("status", status)
                .setParameter("idUser", idUser)
                .getResultList();

        // Nhóm dữ liệu theo `id`
        Map<Long, TransferListOderStatusDTO> groupedOrders = new LinkedHashMap<>();
        for (Object[] row : result) {
            Long id = (Long) row[0];
            LocalDateTime date = (LocalDateTime) row[1];
            String img = (String) row[2];

            groupedOrders.computeIfAbsent(id, key -> new TransferListOderStatusDTO(id, date, new ArrayList<>()))
                    .addImg(img);
        }

        return new ArrayList<>(groupedOrders.values());
    }
}
