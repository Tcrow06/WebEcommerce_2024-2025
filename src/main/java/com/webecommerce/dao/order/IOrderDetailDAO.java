package com.webecommerce.dao.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;

import java.util.List;

public interface IOrderDetailDAO extends GenericDAO<OrderDetailEntity> {
    List<OrderDetailEntity> findAllByOrderId(Long orderId);
    OrderDetailEntity findProductVariantById(Long orderId);
}
