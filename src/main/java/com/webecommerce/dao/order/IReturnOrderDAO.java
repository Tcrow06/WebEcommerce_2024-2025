package com.webecommerce.dao.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.order.ReturnOrderEntity;

import java.util.List;

public interface IReturnOrderDAO extends GenericDAO <ReturnOrderEntity> {
    List<Object[]> getData();
    Object[] getCustomerByReturnOrderId(Long returnOrderId);
    List<Object[]> getProductReturnData(Long returnOrderId);
    long updateStatus(Long returnOrderId);
    boolean updateStatusOrder(Long returnOrderId);
    //boolean updateStatusNoReturn(Long returnOrderId);
    long updateStatusProcess(Long orderDetailId);
}
