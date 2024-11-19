package com.webecommerce.dao.order;


import com.webecommerce.dao.GenericDAO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.OrderInfoEntity;

import java.util.List;

public interface IOrderInfoDAO extends GenericDAO <OrderInfoEntity> {
    List<OrderInfoEntity> findOrderInfoByCustomerId(Long customerId);

    boolean setOrderInfoDefault(OrderInfoEntity orderInfo);

    OrderInfoEntity findDefaultOrderInfoByUserId(Long idUser);
}
