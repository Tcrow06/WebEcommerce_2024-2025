package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dao.other.IAccountDAO;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.other.AccountResponse;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.dto.response.people.UserResponse;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.exception.DuplicateFieldException;
import com.webecommerce.mapper.IAccountMapper;
import com.webecommerce.mapper.ICustomerMapper;
import com.webecommerce.mapper.Impl.AccountMapper;
import com.webecommerce.service.IAccountService;
import com.webecommerce.service.ICacheService;
import com.webecommerce.utils.EmailUtils;
import com.webecommerce.utils.RandomUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class AccountService implements IAccountService {

    @Inject
    private ICustomerMapper customerMapper;
    @Inject
    private IAccountDAO accountDAO;

    @Inject
    private ICacheService cacheService;

    @Inject
    private IAccountMapper accountMapper;
    @Override
    public UserResponse findByUserNameAndPasswordAndStatus(String userName, String password, String status) {
        return accountDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
    }

    @Transactional
    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        if (accountDAO.existsByEmail(customerRequest.getEmail())) {
            throw new DuplicateFieldException("email");
        }
        if (accountDAO.existsByPhone(customerRequest.getPhone())) {
            throw new DuplicateFieldException("phone");
        }
        if (accountDAO.existsByUsername(customerRequest.getUserName())) {
            throw new DuplicateFieldException("username");
        }

        CustomerEntity customerEntity = customerMapper.toCustomerEntityFull(customerRequest);
        customerEntity.setCart(new CartEntity());
        AccountEntity accountEntity = accountMapper.toAccountEntity(customerRequest);
        accountEntity.setCustomer(customerEntity);
        accountDAO.insert(accountEntity);
        return customerMapper.toCustomerResponse(accountEntity.getCustomer());
    }

    @Override
    public boolean sendOTPToEmail(String email, long id) {
        try {
            int otp = RandomUtils.generateSixDigit();

            // Set OTP To Redis
            String key = String.format("user:%s:otp", id);
            System.out.println(key);
            System.out.println(otp);
            cacheService.setKey(key, String.valueOf(otp), 60 * 3);
            String keyCount = String.format("user:%s:otp:count", id);
            cacheService.setKey(keyCount, "0", 60 * 3);

            // Send Email
            String subject = "Mã xác thực (OTP) để hoàn tất đăng ký tài khoản của bạn";
            String body = "Xin chào,\n\n"
                    + "Cảm ơn bạn đã đăng ký tài khoản của chúng tôi! "
                    + "Để hoàn tất quá trình đăng ký, vui lòng nhập mã xác thực OTP dưới đây:\n\n"
                    + "Mã OTP của bạn là: " + otp + "\n\n"
                    + "Lưu ý: Mã OTP này sẽ hết hạn sau 3 phút.\n\n";
//            EmailUtils.sendEmail(email, subject, body);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int verifyOTP(String id, String otp) {
        String key = String.format("user:%s:otp", id);
        String otpFound = cacheService.getKey(key);
        String otpCountKey = String.format("user:%s:otp:count", id);
        if (otpFound.equals(otp)) {
            // Update Active
            AccountEntity accountEntity = accountDAO.findById(Long.parseLong(id));
            accountEntity.setStatus(EnumAccountStatus.ACTIVE);
            accountDAO.update(accountEntity);
            return 0;
        } else {
            cacheService.increment(otpCountKey);
            String otpCount = cacheService.getKey(otpCountKey);
            if (otpFound.equals("5")) {
                cacheService.delete(key);
            }
            return Integer.parseInt(otpCount);
        }
    }
}
