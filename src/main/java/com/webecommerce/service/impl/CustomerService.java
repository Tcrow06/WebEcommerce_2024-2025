package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dao.other.IAccountDAO;
import com.webecommerce.dao.other.ISocialAccountDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.notinentity.ManageUserDTO;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.other.SocialAccountEntity;
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
    @Inject
    private IAccountDAO accountDAO;
    @Inject
    private ISocialAccountDAO socialAccountDAO;
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
        CustomerEntity customer = customerDAO.findById(userId);
        if (customer != null) {
            if (customer.getAccount() != null && customer.getAccount().getId() != null) {
                AccountEntity account = accountDAO.findById(customer.getAccount().getId());
                if (account != null) {
                    account.setStatus(status);
                    return accountDAO.update(account) != null;
                }
            }

            if (customer.getSocialAccount() != null && customer.getSocialAccount().getId() != null) {
                SocialAccountEntity socialAccount = socialAccountDAO.findById(customer.getSocialAccount().getId());
                if (socialAccount != null) {
                    socialAccount.setStatus(status);
                    return socialAccountDAO.update(socialAccount) != null;
                }
            }
        }
        return false;
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
