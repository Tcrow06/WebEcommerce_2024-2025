package com.webecommerce.dao.impl.people;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.notinentity.ManageUserDTO;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.other.SocialAccountEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO extends AbstractDAO<CustomerEntity> implements ICustomerDAO {

    private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    public CustomerDAO() {
        super(CustomerEntity.class);
    }


    @Override
    public CustomerEntity findByEmail(String email) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        CustomerEntity customer = null;
        try {
            trans.begin();
            String query = "SELECT e FROM CustomerEntity e WHERE e.email = :email";
            customer = em.createQuery(query, CustomerEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            trans.commit();
        }catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "No customer found with email: " + email);
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm đối tượng theo email", e);
        } finally {
            em.close();
        }
        return customer;
    }


    public CustomerEntity findById(long id) {
        try {
            String jpql = "SELECT u FROM CustomerEntity u WHERE u.id = :id";
            return entityManager.createQuery(jpql, CustomerEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public int totalCustomers() {
        String query = "SELECT COUNT(p) FROM CustomerEntity p"; // Đếm tổng số sản phẩm
        try {
            Long count = entityManager.createQuery(query, Long.class)
                    .getSingleResult();
            return count != null ? count.intValue() : 0; // Chuyển đổi Long thành int
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tính tổng số sản phẩm", e);
            return 0; // Trả về 0 nếu xảy ra lỗi
        }
    }

    @Override
    public List<ManageUserDTO> getInfoUser() {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            String jpql = """
            SELECT cus.id AS customerId,
                   cus.name AS customerName,
                   cus.phone AS customerPhone,
                   cus.loyaltyPoint AS customerPoint,
                   COALESCE(a.status, sa.status) AS status,
                   COALESCE(COUNT(DISTINCT o2.id), 0) AS numOfCancel,
                   CASE WHEN COALESCE(COUNT(DISTINCT o2.id), 0) >= 5 THEN 1 ELSE 0 END AS isHighCancel
            FROM CustomerEntity cus
            LEFT JOIN cus.account a
            LEFT JOIN cus.socialAccount sa
            LEFT JOIN cus.orders o2
                 ON (o2.id IN (
                     SELECT os1.order.id
                     FROM OrderStatusEntity os1
                     GROUP BY os1.order.id
                     HAVING COUNT(DISTINCT os1.status) = 2
                        AND SUM(CASE WHEN os1.status = 'CANCELLED' THEN 1 ELSE 0 END) > 0
                 ))
            WHERE a.role = 'CUSTOMER' OR sa.id != NULL
            GROUP BY cus.id, cus.name, cus.phone, cus.loyaltyPoint, a.status, sa.status
""";


            List<Object[]> rawResults = entityManager.createQuery(jpql, Object[].class)
                    .getResultList();

            List<ManageUserDTO> resultList = new ArrayList<>();

            for (Object[] result : rawResults) {
                Long customerId = (Long) result[0];
                String name = result[1].toString();
                String phone;
                if (result[2] == null) {
                    phone = "null";
                } else {
                    phone = result[2].toString();
                }
                Integer point = (Integer) result[3];
                EnumAccountStatus status = (EnumAccountStatus) result[4];
                Long numOfCancel = (Long) result[5];
                Integer block = (Integer) result[6];

                if (block.equals(1)) {
                    AccountEntity account = entityManager.createQuery(
                                    "SELECT a FROM AccountEntity a WHERE a.customer.id = :customerId", AccountEntity.class)
                            .setParameter("customerId", customerId)
                            .getSingleResult();
                    if (account != null) {
                        account.setStatus(EnumAccountStatus.BLOCK);
                        entityManager.merge(account);
                    } else {
                        SocialAccountEntity socialAccount = entityManager.createQuery(
                                        "SELECT sa FROM SocialAccountEntity sa WHERE sa.customer.id = :customerId", SocialAccountEntity.class)
                                .setParameter("customerId", customerId)
                                .getSingleResult();
                        socialAccount.setStatus(EnumAccountStatus.BLOCK);
                        entityManager.merge(socialAccount);
                    }
                }

                if (status.equals(EnumAccountStatus.UNVERIFIED)) {
                    continue;
                }
                resultList.add(new ManageUserDTO(customerId, name, phone, point, numOfCancel, status, block));
            }
            transaction.commit();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public boolean updateStatusAccount(Long userId, EnumAccountStatus status) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            String queryAccount = "UPDATE AccountEntity a SET a.status = :status WHERE a.customer.id = :userId";
            Query jpqlQueryAccount = entityManager.createQuery(queryAccount);
            jpqlQueryAccount.setParameter("status", status);
            jpqlQueryAccount.setParameter("userId", userId);

            int updated = jpqlQueryAccount.executeUpdate();

            if(updated == 0) {

                String query = "UPDATE SocialAccountEntity sa SET sa.status = :status WHERE sa.customer.id = :userId";
                Query jpqlQuerySocialAccount = entityManager.createQuery(query);
                jpqlQuerySocialAccount.setParameter("status", status);
                jpqlQuerySocialAccount.setParameter("userId", userId);

                updated = jpqlQuerySocialAccount.executeUpdate();

                if(updated == 0) {
                    transaction.rollback();
                    return false;
                }
            }
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if(transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean updateLoyalPoint(double total, Long customerId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            double point = total / 100000;
            if(total < 100000) {
                point = 0;
            }
            String queryAccount = "UPDATE CustomerEntity c SET c.loyaltyPoint = c.loyaltyPoint + :point WHERE c.id = :customerId";
            Query jpqlQueryAccount = entityManager.createQuery(queryAccount);
            jpqlQueryAccount.setParameter("point", (int)point);
            jpqlQueryAccount.setParameter("customerId", customerId);

            int updated = jpqlQueryAccount.executeUpdate();

            if(updated == 0) {
                transaction.rollback();
                return false;
            }
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if(transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
}
