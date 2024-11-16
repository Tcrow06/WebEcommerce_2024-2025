package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.logging.Level;

public class OrderDAO extends AbstractDAO<OrderEntity> implements IOrderDAO {

    public OrderDAO() {
        super(OrderEntity.class);
    }

    @Override
    public OrderEntity merge(OrderEntity orderEntity) {
        EntityManager em = HibernateUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        trans.begin();
        try {
            // Sử dụng merge() thay vì persist()
            em.persist(orderEntity);  // Nếu entity đã tồn tại, nó sẽ được cập nhật; nếu chưa tồn tại, sẽ được chèn mới
            em.flush();  // Đảm bảo dữ liệu được ghi vào DB
            em.clear();  // Làm trống bộ nhớ đệm sau khi ghi
            trans.commit();  // Commit giao dịch
            return null;
//            LOGGER.log(Level.INFO, "Merged object: {0}", mergedEntity);
//            return mergedEntity;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error merging object", e);
            trans.rollback();  // Rollback nếu có lỗi
            return null;
        } finally {
            em.close();  // Đóng EntityManager sau khi hoàn tất
        }
    }
}
