package com.webecommerce.dao.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.entity.order.OrderDetailEntity;

import java.util.List;

public interface IOrderDetailDAO extends GenericDAO<OrderDetailEntity> {
    List<OrderDetailEntity> findAllByOrderId(Long orderId);

    List<DisplayOrderDetailDTO> showOrderDetail(Long orderId, EnumOrderStatus status);
    EnumOrderStatus getCurrentStatus(Long orderId);

    DisplayOrderDetailDTO findOrderDetail(Long orderDetailId);
    List<DisplayOrderDetailDTO> showOrderDetailAdmin(Long orderId);


}
