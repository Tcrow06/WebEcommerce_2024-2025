package com.webecommerce.service.impl;

import com.webecommerce.dto.OrderDTO;
import com.webecommerce.service.IOrderService;
import com.webecommerce.service.IPaymentService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class PaymentService implements IPaymentService {

    @Inject
    IOrderService orderService;


    @Override
    public boolean checkPayment(List<Map<String, String>> dataFilter, OrderDTO orderDTO, Long idUser) {

        OrderDTO result = null;

        if (dataFilter == null || dataFilter.size() < 2) {
            return false;
        }

        Map<String, String> lastItem = dataFilter.get(dataFilter.size() - 1);

        String lastDescription = lastItem.get("description");
        double lastAmount = Long.parseLong(lastItem.get("amount"));

        if (lastDescription.equals(orderDTO.getOrderInfoDTO().getPhone())
                && lastAmount == (orderDTO.getTotal())) {
            result = orderService.findInfoPayment(orderDTO, idUser);
        }

        return result.getStatus().equals("success") && lastDescription.equals(orderDTO.getOrderInfoDTO().getPhone())
                && lastAmount == (orderDTO.getTotal());
    }
}
