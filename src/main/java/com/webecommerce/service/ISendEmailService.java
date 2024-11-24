package com.webecommerce.service;

import com.webecommerce.dto.OrderDTO;

public interface ISendEmailService {
    void sendEmail(OrderDTO order, Long userId);
}
