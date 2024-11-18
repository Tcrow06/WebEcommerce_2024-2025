package com.webecommerce.service;

import com.webecommerce.dto.ReturnOrderDTO;
import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.dto.response.people.CustomerResponse;

import java.util.List;

public interface IReturnOrderService {
    ReturnOrderDTO save(ReturnOrderDTO returnOrderDTO);

    List<TransferListDTO> getData();

    CustomerResponse getCustomerData(Long returnOrderId);
    ProductReturnDTO getProductReturnData(Long returnOrderId);
    boolean updateStatus(Long returnOrderId);
    boolean updateStatusOrder(Long returnOrderId);
    boolean updateStatusNoReturn(Long returnOrderId);
    boolean updateStatusProcess(Long orderDetailId);

}
