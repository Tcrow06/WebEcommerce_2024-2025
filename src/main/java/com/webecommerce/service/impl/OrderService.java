package com.webecommerce.service.impl;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dto.OrderDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.service.IBillDiscountService;
import com.webecommerce.service.IOrderService;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import java.util.List;

public class OrderService implements IOrderService {

    @Inject
    private IProductService productService;

    @Inject
    private IBillDiscountService billDiscountService;

    @Inject
    private IBillDiscountDAO billDiscountDAO;









    @Override
    public OrderDTO findInfoCheckOut(CheckOutRequestDTO checkOutRequestDTO) {
        OrderEntity order = new OrderEntity();
//        order.setBillDiscount(billDiscountDAO.findById());
        return null;

    }
}
