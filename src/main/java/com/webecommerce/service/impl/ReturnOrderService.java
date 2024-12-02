package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.impl.order.OrderDetailDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.dao.order.IReturnOrderDAO;
import com.webecommerce.dto.OrderStatusDTO;
import com.webecommerce.dto.ReturnOrderDTO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import com.webecommerce.entity.product.CategoryEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.Impl.OrderStatusMapper;
import com.webecommerce.mapper.Impl.ReturnOrderMapper;
import com.webecommerce.service.IReturnOrderService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReturnOrderService implements IReturnOrderService {
    @Inject
    private ReturnOrderMapper returnOrderMapper;
    @Inject
    private OrderStatusMapper orderStatusMapper;
    @Inject
    private IOrderStatusDAO orderStatusDAO;
    @Inject
    private IReturnOrderDAO returnOrderDAO;

    @Inject
    private OrderDetailDAO orderDetailDAO;

    @Inject
    private IOrderDAO orderDAO;

    @Override
    public ReturnOrderDTO save(ReturnOrderDTO returnOrderDTO) {
        ReturnOrderEntity returnOrderEntity = returnOrderMapper.toEntity(returnOrderDTO);
        if(returnOrderEntity == null)
            return null;


        returnOrderEntity.setOrderDetail(orderDetailDAO.findById(returnOrderDTO.getOrderDetailId()));
        returnOrderEntity.getOrderDetail().setReturnOrder(returnOrderEntity);

        orderDetailDAO.update(returnOrderEntity.getOrderDetail());

        if (returnOrderEntity.getOrderDetail() == null)
            return null;
        else {
            return returnOrderMapper.toDTO(
                    returnOrderEntity
            );
        }
    }

    @Override
    public List<TransferListDTO> getData() {
        List<Object[]> listData = returnOrderDAO.getData();

        List<TransferListDTO> transferItems = new ArrayList<>();
        for (Object[] row : listData) {
            Long id = (Long) row[0];
            LocalDate returnDate = (LocalDate) row[1];
            int status = (Integer) row[2];
            Long quantityReturn = (Long) row[3];
            String color = (String) row[4];
            String productName = (String) row[5];
            Long orderId = orderDAO.findOrderId(id);
            TransferListDTO item = new TransferListDTO(id, returnDate, productName,color, quantityReturn , status, orderId);
            transferItems.add(item);
        }
        return transferItems;
    }

    @Override
    public CustomerResponse getCustomerData(Long returnOrderId) {
        Object[] result = returnOrderDAO.getCustomerByReturnOrderId(returnOrderId);

        Long customerId = (Long) result[0];
        String customerName = (String) result[1];
        String phone = (String) result[2];
        String email = (String) result[3];

        return new CustomerResponse(customerId, customerName, phone, email);
    }

    @Override
    public ProductReturnDTO getProductReturnData(Long returnOrderId) {
        List<Object[]> result = returnOrderDAO.getProductReturnData(returnOrderId);

        ProductReturnDTO item = null;

        for (Object[] row : result) {
            Long id = (Long) row[0];
            LocalDate returnDate = (LocalDate) row[1];
            int status = (Integer) row[2];
            Long quantityReturn = (Long) row[3];
            String color = (String) row[4];
            String imageUrl = (String) row[5];
            String size = (String) row[6];
            String productName = (String) row[7];
            double price = (Double) row[8]; // Trả về giá trị kiểu double
            Integer discountPercentage = row[9] != null ? (Integer) row[9] : 0; // If null, set default to 0.0
            String reason = (String) row[10];

            double discountMultiplier = 1.0 - (discountPercentage / 100.0);
            double saleProduct = price * discountMultiplier;

            item = new ProductReturnDTO(
                    id, returnDate, status, quantityReturn, color, imageUrl, size, productName, saleProduct, reason);
        }

        return item;
    }

    @Override
    public boolean updateStatus(Long returnOrderId) {

        long result = returnOrderDAO.updateStatus(returnOrderId);
        long orderId = orderDAO.findOrderId(returnOrderId);
        if (result == 0) {
            OrderStatusEntity newOrderStatus = new OrderStatusEntity();
            newOrderStatus.setOrder(orderDAO.findById(orderId));
            newOrderStatus.setStatus(EnumOrderStatus.valueOf("CANCELLED"));
            newOrderStatus.setDate(LocalDateTime.now());

            OrderStatusDTO dto = orderStatusMapper.toDTO(orderStatusDAO.insert(newOrderStatus));
            return dto != null;
        }
        return false;
    }

    @Override
    public boolean updateStatusOrder(Long returnOrderId) {
        return returnOrderDAO.updateStatusOrder(returnOrderId);
    }

    @Override
    public boolean updateStatusNoReturn(Long returnOrderId) {
        OrderDetailEntity orderDetailEntity = orderDetailDAO.findById(returnOrderId);
        ReturnOrderEntity returnOrderEntity = returnOrderDAO.findById(orderDetailEntity.getReturnOrder().getId());
        returnOrderEntity.setStatus(2);

        if(returnOrderDAO.update(returnOrderEntity) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStatusProcess(Long orderDetailId) {
        long result = returnOrderDAO.updateStatusProcess(orderDetailId);

        long orderId = orderDAO.findOrderId(orderDetailId);
        if (result == 0) {
            OrderStatusEntity newOrderStatus = new OrderStatusEntity();
            newOrderStatus.setOrder(orderDAO.findById(orderId));
            newOrderStatus.setStatus(EnumOrderStatus.PROCESSED);
            newOrderStatus.setDate(LocalDateTime.now());

            OrderStatusDTO dto = orderStatusMapper.toDTO(orderStatusDAO.insert(newOrderStatus));
            return dto != null;
        }
        return false;

    }


}
