package com.webecommerce.dao.people;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.notinentity.ManageUserDTO;
import com.webecommerce.entity.people.CustomerEntity;

import java.util.List;

public interface ICustomerDAO extends GenericDAO<CustomerEntity> {
    CustomerEntity findByEmail(String email);
    CustomerEntity findById(long id);
    int totalCustomers();
    List<ManageUserDTO> getInfoUser();
    boolean updateStatusAccount(Long userId, EnumAccountStatus status);
    boolean updateLoyalPoint(double total, Long customerId);

}
