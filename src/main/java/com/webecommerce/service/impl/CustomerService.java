package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.notinentity.ManageUserDTO;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.mapper.ICustomerMapper;
import com.webecommerce.service.ICustomerService;

import javax.inject.Inject;
import java.util.List;

public class CustomerService implements ICustomerService {

    @Inject
    private ICustomerDAO customerDAO;
    @Inject
    private ICustomerMapper customerMapper;
    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerRequest.getName());
        customerDAO.insert(customerEntity);
        return customerMapper.toCustomerResponse(customerEntity);
    }

    @Override
    public CustomerResponse findById(Long id) {
        CustomerEntity customerEntity = customerDAO.findById(id);
        return customerMapper.toCustomerResponse(customerEntity);
    }

    @Override
    public int getPointLoyaltyCustomer(Long id) {
        CustomerEntity customerEntity = customerDAO.findById(id);
        int p = customerEntity.getLoyaltyPoint();
        return p;
    }

    @Override
    public List<ManageUserDTO> getInfoUser() {
        return customerDAO.getInfoUser();
    }

    @Override
    public boolean updateStatusAccount(Long userId, EnumAccountStatus status) {
        return customerDAO.updateStatusAccount(userId, status);
    }

    @Override
    public boolean updateLoyalPoint(double total, Long customerId) {
        return customerDAO.updateLoyalPoint(total, customerId);
    }

    @Override
    public CustomerResponse findByEmail(String email) {
        CustomerEntity customerEntity = customerDAO.findByEmail(email);
        return customerMapper.toCustomerResponse(customerEntity);
    }

    public String updateInforCustomer(String id, String name, String email, String Phone) {
        try {
            CustomerEntity customerEntity = customerDAO.findById(Long.parseLong(id));
            customerEntity.setName(name);
            customerEntity.setEmail(email);
            customerEntity.setPhone(Phone);
            customerDAO.update(customerEntity);
            return "oke";
        } catch  (Exception e){
            return "error";
        }
    }
}
