package com.webecommerce.service.impl;

import com.webecommerce.service.IPaymentService;

import java.util.List;
import java.util.Map;

public class PaymentService implements IPaymentService {

    @Override
    public boolean checkPayment(List<Map<String, String>> dataFilter) {
        if (dataFilter == null || dataFilter.size() < 2) {
            return false;
        }

        Map<String, String> lastItem = dataFilter.get(dataFilter.size() - 1);
        Map<String, String> secondLastItem = dataFilter.get(dataFilter.size() - 2);

        String lastDescription = lastItem.get("description");
        String lastAmount = lastItem.get("amount");
        String secondLastDescription = secondLastItem.get("description");
        String secondLastAmount = secondLastItem.get("amount");

        // Kiểm tra nếu cả description và amount đều bằng nhau
        return lastDescription != null && lastDescription.equals(secondLastDescription) &&
                lastAmount != null && lastAmount.equals(secondLastAmount);
    }
}
