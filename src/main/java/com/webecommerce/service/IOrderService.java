package com.webecommerce.service;

import com.webecommerce.dto.OrderDTO;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;

import java.util.List;

public interface IOrderService {







    OrderDTO findInfoCheckOut(CheckOutRequestDTO checkOutRequestDTO);
    OrderDTO findInfoPayment(OrderDTO orderDTO, Long idUser);
}
