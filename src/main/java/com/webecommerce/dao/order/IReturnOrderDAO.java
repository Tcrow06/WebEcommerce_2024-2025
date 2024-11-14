package com.webecommerce.dao.order;

import com.webecommerce.dao.GenericDAO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.entity.order.ReturnOrderEntity;

import java.util.List;

public interface IReturnOrderDAO extends GenericDAO <ReturnOrderEntity> {
    List<TransferListDTO> getData();
}
