package com.webecommerce.dao.order;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.product.ProductVariantEntity;

import java.util.List;
import java.util.Map;

public interface IOrderDAO extends GenericDAO<OrderEntity> {
    List<DisplayOrderDTO> getOrderDisplay();
    List<DisplayOrderDTO> getOrderDisplay(Long customerId);

    OrderEntity merge(OrderEntity orderEntity);
    List<ProductVariantEntity> getBestSellingProduct();

    List<Object[]> calculateMonthlyRevenue(int year);
    Double calculateTotalRevenueByYear(int year);
    Double calculateTotalRevenue();
    int totalOrdersByStatus(EnumOrderStatus status);
    int totalOrdersToday();
    boolean changeConfirmStatus(Long orderId);
    List<DisplayOrderDTO> getListOrder();

    Long findOrderId(Long orderDetailId);
}
