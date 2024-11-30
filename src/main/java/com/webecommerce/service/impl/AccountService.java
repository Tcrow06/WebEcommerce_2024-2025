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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        if (accountDAO.existsByPhone(customerRequest.getPhone())) {
            throw new DuplicateFieldException("phone");
        }
            if (accountDAO.existsByEmail(customerRequest.getEmail())) {
                throw new DuplicateFieldException("email");
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

    public AccountResponse findByCustomerId(Long idCustomer) {
        AccountEntity accountEntity = accountDAO.findByCustomerId(idCustomer);
        return accountMapper.toAccountResponse(accountEntity);
    }
    @Override
    public void setPassword(long id, String password) {
        AccountEntity accountEntity = accountDAO.findByCustomerId(id);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        accountEntity.setPassword(passwordEncoder.encode(password));
        accountDAO.update(accountEntity);
    }

    @Override
    public boolean existsUsernameAndEmail(String username, String email) {
        if (accountDAO.existsByUsername(username) && accountDAO.existsByEmail(email)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean sendOTPToEmail(String email, long id, String purpose) {
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
            String subject = null;
            String body = null;
            if (purpose.equals("register")) {
                subject = "Mã xác thực (OTP) để hoàn tất đăng ký tài khoản của bạn";
                body = "Xin chào,\n\n"
                        + "Cảm ơn bạn đã đăng ký tài khoản của chúng tôi! "
                        + "Để hoàn tất quá trình đăng ký, vui lòng nhập mã xác thực OTP dưới đây:\n\n"
                        + "Mã OTP của bạn là: " + otp + "\n\n"
                        + "Lưu ý: Mã OTP này sẽ hết hạn sau 3 phút.\n\n";
            } else {
                subject = "Mã xác thực (OTP) để đặt lại mật khẩu của bạn";
                body = "Mã OTP của bạn là: " + otp + "\n\n"
                        + "Lưu ý: Mã OTP này sẽ hết hạn sau 3 phút.\n\n";
            }

           EmailUtils.sendEmail(email, subject, body);
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
        AccountEntity accountEntity = accountDAO.findByCustomerId(Long.parseLong(id));

        if (otpFound == null) {
            int newOtp = RandomUtils.generateSixDigit();
            cacheService.setKey(key, String.valueOf(newOtp), 60 * 3); // TTL 3 phút
            cacheService.setKey(otpCountKey, "0", 60 * 3); // Reset số lần nhập sai

            String subject = "Mã xác thực (OTP) để hoàn tất đăng ký tài khoản của bạn";
            String body = "Xin chào,\n\n"
                    + "OTP của bạn đã hết hạn. Chúng tôi đã gửi mã xác thực OTP mới để hoàn tất quá trình đăng ký:\n\n"
                    + "Mã OTP mới của bạn là: " + newOtp + "\n\n"
                    + "Lưu ý: Mã OTP này sẽ hết hạn sau 3 phút.\n\n";

            EmailUtils.sendEmail(accountEntity.getCustomer().getEmail(), subject, body);
            return -2; // OTP hết hạn và đã gửi lại mã mới
        }

        if (otpFound.equals(otp)) {
            // Update Active
            accountEntity = accountDAO.findByCustomerId(Long.parseLong(id));
            accountEntity.setStatus(EnumAccountStatus.ACTIVE);
            accountDAO.update(accountEntity);

            cacheService.delete(key);
            cacheService.delete(otpCountKey);
            return 0; // Thanh cong
        } else {
            cacheService.increment(otpCountKey);
            String otpCount = cacheService.getKey(otpCountKey);
            if (Integer.parseInt(otpCount) >= 5) {
                int newOtp = RandomUtils.generateSixDigit();
                System.out.println(newOtp);
                cacheService.setKey(key, String.valueOf(newOtp), 60 * 3);
                cacheService.setKey(otpCountKey, "0", 60 * 3);

                String subject = "Mã xác thực (OTP) để hoàn tất đăng ký tài khoản của bạn";
                String body = "Xin chào,\n\n"
                        + "Cảm ơn bạn đã đăng ký tài khoản của chúng tôi! "
                        + "Để hoàn tất quá trình đăng ký, vui lòng nhập mã xác thực OTP dưới đây:\n\n"
                        + "Mã OTP của bạn là: " + newOtp + "\n\n"
                        + "Lưu ý: Mã OTP này sẽ hết hạn sau 3 phút.\n\n";

                EmailUtils.sendEmail(accountEntity.getCustomer().getEmail(), subject, body);
                return -1; // Sai qua 5 lan, gui ma OTP moi
            }
            return Integer.parseInt(otpCount); // So lan nhap sai
        }
    }

    @Override
    public String checkLogin(AccountRequest accountRequest) {
        StringBuilder message = new StringBuilder();
        if(accountRequest.getUserName()==null || accountRequest.getUserName().trim().isEmpty()){
            message.append("username_not_null");
        }
        if(accountRequest.getPassword()==null || accountRequest.getPassword().trim().isEmpty()){
            message.append("password_not_null");
        }
        return message.toString();
    }

    @Override
    public UserResponse findByUserNameAndPassword(String userName, String password) {
        return accountDAO.findByUserNameAndPassword(userName,password);
    }
}
