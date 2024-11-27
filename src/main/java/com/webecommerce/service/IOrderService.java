package com.webecommerce.service;

import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.OrderDTO;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;

import java.util.List;

public interface IOrderService {
    List<DisplayOrderDTO> getOrderDisplay();
    List<DisplayOrderDTO> getOrderDisplay(Long customerId);
    OrderDTO findInfoCheckOut(CheckOutRequestDTO checkOutRequestDTO);
    OrderDTO findInfoPayment(OrderDTO orderDTO, Long idUser);
    boolean changeConfirmStatus(Long orderId);
    List<DisplayOrderDTO> getListOrder();

}
