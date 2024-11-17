package com.webecommerce.service.impl;

import com.webecommerce.dao.impl.order.OrderDetailDAO;
import com.webecommerce.dao.order.IReturnOrderDAO;
import com.webecommerce.dto.ReturnOrderDTO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import com.webecommerce.entity.product.CategoryEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.Impl.ReturnOrderMapper;
import com.webecommerce.service.IReturnOrderService;

import javax.inject.Inject;
import java.util.List;

public class ReturnOrderService implements IReturnOrderService {
    @Inject
    private ReturnOrderMapper returnOrderMapper;

    @Inject
    private IReturnOrderDAO returnOrderDAO;

    @Inject
    private OrderDetailDAO orderDetailDAO;

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
        return returnOrderDAO.getData();
    }

    @Override
    public CustomerResponse getCustomerData(Long returnOrderId) {
        return returnOrderDAO.getCustomerByReturnOrderId(returnOrderId);
    }

    @Override
    public ProductReturnDTO getProductReturnData(Long returnOrderId) {
        return returnOrderDAO.getProductReturnData(returnOrderId);
    }

    @Override
    public boolean updateStatus(Long returnOrderId) {
        return returnOrderDAO.updateStatus(returnOrderId);
    }

    @Override
    public boolean updateStatusOrder(Long returnOrderId) {
        return returnOrderDAO.updateStatusOrder(returnOrderId);
    }

    @Override
    public boolean updateStatusNoReturn(Long returnOrderId) {
        return returnOrderDAO.updateStatusNoReturn(returnOrderId);
    }

    @Override
    public boolean updateStatusProcess(Long orderDetailId) {
        return returnOrderDAO.updateStatusProcess(orderDetailId);

    }


}
