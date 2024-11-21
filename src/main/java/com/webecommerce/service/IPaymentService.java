package com.webecommerce.service;

import java.util.List;
import java.util.Map;

public interface IPaymentService {
    boolean checkPayment(List<Map<String, String>> dataFilter);
}
