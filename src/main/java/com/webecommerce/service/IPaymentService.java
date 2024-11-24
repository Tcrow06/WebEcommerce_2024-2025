package com.webecommerce.service;

import com.webecommerce.dto.OrderDTO;

import java.util.List;
import java.util.Map;

public interface IPaymentService {
    boolean checkPayment(List<Map<String, String>> dataFilter, OrderDTO orderDTO, Long idUser);
}
