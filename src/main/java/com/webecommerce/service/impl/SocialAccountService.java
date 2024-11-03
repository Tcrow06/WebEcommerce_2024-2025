package com.webecommerce.service.impl;

import com.webecommerce.dao.other.ISocialAccountDAO;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.other.SocialAccountEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.mapper.ICustomerMapper;
import com.webecommerce.mapper.ISocialAccountMapper;
import com.webecommerce.service.ISocialAccountService;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class SocialAccountService implements ISocialAccountService {
    @Inject
    private CustomerService customerService;
    @Inject
    private ISocialAccountDAO socialAccountDAO;
    @Inject
    private ICustomerMapper customerMapper;

    @Inject
    private ISocialAccountMapper SocialAccountMapper;

    @Override
    public CustomerResponse findByFbID(String fbID) {
        SocialAccountEntity acc = socialAccountDAO.findByFbID(fbID);
        if(acc !=null)
            return customerMapper.toCustomerResponse(acc.getCustomer());
        return null;
    }

    @Override
    public CustomerResponse findByGgID(String ggID) {
        SocialAccountEntity acc = socialAccountDAO.findByGgID(ggID);
        if(acc !=null)
            return customerMapper.toCustomerResponse(acc.getCustomer());
        return null;
    }

    @Transactional
    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {

        CustomerEntity customerEntity = customerMapper.toCustomerEntity(customerRequest);
        SocialAccountEntity socialAccountEntity = SocialAccountMapper.toSocialAccountEntity(customerRequest);
        socialAccountEntity.setCustomer(customerEntity);
        socialAccountDAO.insert(socialAccountEntity);
        return customerMapper.toCustomerResponse(socialAccountEntity.getCustomer());

//        CustomerResponse customerResponse = customerService.save(customerRequest);
        ///Lưu Acc

//        return customerResponse;
    }
}
