package com.webecommerce.service;

import com.webecommerce.dto.ReturnOrderDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;

import java.util.List;

public interface IReturnOrderService {
    ReturnOrderDTO save(ReturnOrderDTO returnOrderDTO);

    List<TransferListDTO> getData();
}
