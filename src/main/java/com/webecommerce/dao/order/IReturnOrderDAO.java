package com.webecommerce.dao.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.order.ReturnOrderEntity;

import java.util.List;

public interface IReturnOrderDAO extends GenericDAO <ReturnOrderEntity> {
    List<TransferListDTO> getData();
    CustomerResponse getCustomerByReturnOrderId(Long returnOrderId);
    ProductReturnDTO getProductReturnData(Long returnOrderId);
    boolean updateStatus(Long returnOrderId);
    boolean updateStatusOrder(Long returnOrderId);
    boolean updateStatusNoReturn(Long returnOrderId);
    boolean updateStatusProcess(Long orderDetailId);
}
