package com.webecommerce.dao.impl.other;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.other.IAccountDAO;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AccountDAODAO extends AbstractDAO<AccountEntity> implements IAccountDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    public AccountDAODAO() {
        super(AccountEntity.class);
        this.entityManagerFactory = HibernateUtil.getEmFactory(); // Giả sử bạn có một HibernateUtil
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public AccountRequest findByUserNameAndPasswordAndStatus(String userName, String password, String status) {
        String jpql = "SELECT a FROM AccountEntity a WHERE a.username = :username AND a.password = :password AND a.status = :status";

        EnumAccountStatus accountStatus;
        try {
            accountStatus = EnumAccountStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Trường hợp giá trị không hợp lệ
            return null; // Hoặc xử lý theo cách khác tùy theo yêu cầu
        }

        List<AccountEntity> resultList = entityManager.createQuery(jpql, AccountEntity.class)
                .setParameter("username", userName)
                .setParameter("password", password)
                .setParameter("status", accountStatus) // Truyền vào giá trị enum
                .getResultList();

        if (resultList != null && !resultList.isEmpty()) {
            AccountEntity accountEntity = resultList.get(0);
            // Ánh xạ từ AccountEntity sang AccountRequest nếu cần
            AccountRequest accountRequest = new AccountRequest();
            accountRequest.setUserName(accountEntity.getUsername());
            accountRequest.setPassword(accountEntity.getPassword());
            accountRequest.setStatus(accountEntity.getStatus().toString());
            accountRequest.setRole(accountEntity.getRole().toString());
            // Thêm các thuộc tính khác nếu cần
            return accountRequest;
        }
        return null;
    }
}
