package com.webecommerce.dao.impl.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dao.impl.AbstractDAO;
import com.webecommerce.dao.order.IReturnOrderDAO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.entity.order.ReturnOrderEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturnOrderDAO extends AbstractDAO<ReturnOrderEntity> implements IReturnOrderDAO {
    public ReturnOrderDAO() {
        super(ReturnOrderEntity.class);
    }

    @Override
    public List<TransferListDTO> getData() {
        String jpql = "SELECT ro.id, ro.returnDate, ro.status, ro.quantityReturn, pv.color, p.name " +
                "FROM ReturnOrderEntity ro " +
                "JOIN ro.orderDetail od " +
                "JOIN od.productVariant pv " +
                "JOIN pv.product p";

        // Thực thi câu lệnh JPQL thông qua EntityManager
        List<Object[]> result = entityManager.createQuery(jpql, Object[].class).getResultList();

        // Khởi tạo DTO để chứa dữ liệu
        // Duyệt qua kết quả và chuyển đổi thành DTO
        List<TransferListDTO> transferItems = new ArrayList<>();
        for (Object[] row : result) {
            Long id = (Long) row[0];
            LocalDate returnDate = (LocalDate) row[1];
            int status = (Integer) row[2];
            Long quantityReturn = (Long) row[3];
            String color = (String) row[4];
            String productName = (String) row[5];

            // Tạo đối tượng TransferItemDTO từ các trường dữ liệu
            TransferListDTO item = new TransferListDTO(id, returnDate,color, productName, quantityReturn , status);
            transferItems.add(item);
        }
        return transferItems;
    }
}
