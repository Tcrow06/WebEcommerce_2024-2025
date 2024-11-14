package com.webecommerce.dao.impl.discount;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;

import java.util.List;

public class BillDiscountDAO extends AbstractDAO<BillDiscountEntity> implements IBillDiscountDAO {

    public BillDiscountDAO() {
        super(BillDiscountEntity.class);
    }
    public List<BillDiscountEntity> getAllDiscountEligible(Long idUser) {
        String query = "SELECT b FROM BillDiscountEntity b JOIN CustomerEntity c " +
                "ON b.loyaltyPointsRequired < c.loyaltyPoint " +
                "WHERE c.id = :idUser " +
                "AND CURRENT_TIMESTAMP BETWEEN b.startDate AND b.endDate";
        EntityManager entityManager = HibernateUtil.getEmFactory().createEntityManager();
        try {

            List<BillDiscountEntity> result = entityManager.createQuery(query, BillDiscountEntity.class)
                    .setParameter("idUser", idUser)
                    .getResultList();

            // Kiểm tra nếu kết quả trả về không có mã giảm giá
            if (result.isEmpty()) {
                LOGGER.log(Level.INFO, "Không có mã giảm giá phù hợp cho khách hàng ID: " + idUser);
            }


            return result;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy bill có loyaltyPointsRequired < loyaltyPoint của khách hàng với ID: " + idUser, e);
            return null;
        }finally {
            entityManager.close();
        }
    }


    public List<BillDiscountEntity> getBillDiscountByOutStanding(boolean outstanding) {
        return super.findByAttribute("isOutStanding", outstanding);
    }

}
