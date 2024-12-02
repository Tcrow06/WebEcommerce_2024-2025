package com.webecommerce.service;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dto.notinentity.ManageUserDTO;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.dto.response.people.UserResponse;

import java.util.List;

public interface ICustomerService {

    CustomerResponse  save(CustomerRequest customerRequest);
    CustomerResponse findById(Long id);
    CustomerResponse findByEmail(String email);
    int getPointLoyaltyCustomer(Long id);

    String updateInforCustomer(String id, String name, String email, String phone);
    List<ManageUserDTO> getInfoUser();
    boolean updateStatusAccount(Long userId, EnumAccountStatus status);
    boolean updateLoyalPoint(double total, Long customerId);
}
