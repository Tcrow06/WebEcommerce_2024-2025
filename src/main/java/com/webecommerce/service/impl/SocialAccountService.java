package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.constant.EnumRole;
import com.webecommerce.dao.impl.people.CustomerDAO;
import com.webecommerce.dao.other.IAccountDAO;
import com.webecommerce.dao.other.ISocialAccountDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.other.AccountEntity;
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

    @Inject
    private ICustomerDAO customerDAO;

    @Inject
    private IAccountDAO accountDAO;

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
        customerEntity.setCart(new CartEntity());
        socialAccountDAO.insert(socialAccountEntity);
        return customerMapper.toCustomerResponse(socialAccountEntity.getCustomer());

    }

    @Transactional
    @Override
    public CustomerResponse saveSocialByEmail(CustomerRequest customerRequest) {
        try {
            SocialAccountEntity acc = socialAccountDAO.findByGgID(customerRequest.getGgID());
            if(acc !=null)
            {
                CustomerResponse customerResponse = customerMapper.toCustomerResponse(acc.getCustomer());
                customerResponse.setStatus(acc.getStatus());
                return customerResponse;
            }

            CustomerEntity customerEntity = customerDAO.findByEmail(customerRequest.getEmail());
            if(customerEntity == null){
                return null;
            }

            SocialAccountEntity socialAccountEntity = new SocialAccountEntity();
            socialAccountEntity.setCustomer(customerEntity);
            socialAccountEntity.setGgID(customerRequest.getGgID());
            AccountEntity account = accountDAO.findByCustomerId(customerEntity.getId());
            socialAccountEntity.setStatus(account.getStatus());
            customerEntity.setSocialAccount(socialAccountEntity);
            if(customerEntity.getAvatar()==null){
                customerEntity.setAvatar(customerRequest.getAvatar());
            }
            if(customerEntity.getCart()==null){
                customerEntity.setCart(new CartEntity());
            }
            customerDAO.update(customerEntity);
            CustomerResponse customerResponse = customerMapper.toCustomerResponse(customerEntity);
            customerResponse.setStatus(account.getStatus());
            return customerResponse;
        }catch (Exception e){
            return new CustomerResponse();
        }
    }

    @Transactional
    @Override
    public CustomerResponse findAccount(CustomerRequest customerRequest, String provider) {
       try{
           SocialAccountEntity socialAccount=null;
           CustomerResponse customerResponse=null;
           if(provider.equals("google")){
               customerResponse = saveSocialByEmail(customerRequest);
           }else {
               socialAccount = socialAccountDAO.findByGgID(customerRequest.getFbID());
           }
           if(socialAccount!=null){
               customerResponse = customerMapper.toCustomerResponse(socialAccount.getCustomer());
               customerResponse.setStatus(socialAccount.getStatus());
           }
           if(customerResponse==null){
               customerResponse = save(customerRequest);
               customerResponse.setStatus(EnumAccountStatus.ACTIVE);
           }
           customerResponse.setRole(EnumRole.CUSTOMER);
           return customerResponse;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }
}
