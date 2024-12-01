package com.webecommerce.dao.impl.other;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.IAccountDAO;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.response.other.AccountResponse;
import com.webecommerce.dto.response.people.UserResponse;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.mapper.IAccountMapper;
import com.webecommerce.mapper.ICustomerMapper;
import com.webecommerce.mapper.IOwnerMapper;
import com.webecommerce.mapper.Impl.AccountMapper;
import com.webecommerce.mapper.Impl.CustomerMapper;
import com.webecommerce.mapper.Impl.OwnerMapper;
import com.webecommerce.utils.HibernateUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AccountDAO extends AbstractDAO<AccountEntity> implements IAccountDAO {

    @Inject
    private IAccountMapper accountMapper;
    private EntityManagerFactory entityManagerFactory;
    @Inject
    private ICustomerMapper customerMapper ;
    @Inject
    private IOwnerMapper ownerMapper ;


    @PersistenceContext
    private EntityManager entityManager;
    public AccountDAO() {
        super(AccountEntity.class);
        this.entityManagerFactory = HibernateUtil.getEmFactory();
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public UserResponse findByUserNameAndPasswordAndStatus(String userName, String password, String status) {
        UserResponse userResponse = new UserResponse();
        String jpql = "SELECT a FROM AccountEntity a WHERE a.username = :username AND a.status = :status";

        EnumAccountStatus accountStatus;
        try {
            accountStatus = EnumAccountStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }

        List<AccountEntity> resultList = entityManager.createQuery(jpql, AccountEntity.class)
                .setParameter("username", userName)
                .setParameter("status", accountStatus)
                .getResultList();

        if (resultList != null && !resultList.isEmpty()) {
            AccountEntity accountEntity = resultList.get(0);
            AccountResponse accountResponse = accountMapper.toAccountResponse(accountEntity);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//            nhớ xóa hàm dưới. đang test
            if(accountResponse.getRole().equals("OWNER") && password.equals(accountEntity.getPassword())) {
                userResponse = ownerMapper.toOwnerResponse(accountEntity.getOwner());
                return userResponse;
            }

            if (passwordEncoder.matches(password, accountEntity.getPassword())) {
                if (accountResponse.getRole().equals("CUSTOMER")) {
                    userResponse = customerMapper.toCustomerResponse(accountEntity.getCustomer());
                } else if (accountResponse.getRole().equals("OWNER")) {
                    userResponse = ownerMapper.toOwnerResponse(accountEntity.getOwner());
                }
                return userResponse;
            }
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        String jpql = "SELECT COUNT(c) FROM CustomerEntity c WHERE c.email = :email";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("email", email);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByPhone(String phone) {
        String jpql = "SELECT COUNT(c) FROM CustomerEntity c WHERE c.phone = :phone";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("phone", phone);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByUsername(String username) {
        String jpql = "SELECT COUNT(a) FROM AccountEntity a WHERE a.username = :username";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("username", username);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public AccountEntity findByCustomerId(Long id) {
        try {
            String jpql = "SELECT a FROM AccountEntity a WHERE a.customer.id = :customerId";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("customerId", id);
            AccountEntity accountEntity = (AccountEntity) query.getSingleResult();
            return accountEntity;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public UserResponse findByUserNameAndPassword(String userName, String password) {
        EntityManager em = getEntityManager();
        UserResponse userResponse = new UserResponse();
        String jpql = "SELECT a FROM AccountEntity a WHERE a.username = :username";

        List<AccountEntity> resultList = em.createQuery(jpql, AccountEntity.class)
                .setParameter("username", userName)
                .getResultList();

        if (resultList != null && !resultList.isEmpty()) {
            AccountEntity accountEntity = resultList.get(0);
            AccountResponse accountResponse = accountMapper.toAccountResponse(accountEntity);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



            if (passwordEncoder.matches(password, accountEntity.getPassword())) {
                if (accountResponse.getRole().equals("CUSTOMER")) {
                    userResponse = customerMapper.toCustomerResponse(accountEntity.getCustomer());
                    userResponse.setStatus(accountEntity.getStatus());
                } else if (accountResponse.getRole().equals("OWNER")) {
                    userResponse = ownerMapper.toOwnerResponse(accountEntity.getOwner());
                    userResponse.setStatus(accountEntity.getStatus());
                }
                return userResponse;
            }
        }
        return null;
    }

}
