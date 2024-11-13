package com.webecommerce.mapper.Impl;

import com.webecommerce.dao.impl.order.OrderDetailDAO;
import com.webecommerce.dto.ReturnOrderDTO;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.ReturnOrderEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ReturnOrderMapper implements GenericMapper<ReturnOrderDTO, ReturnOrderEntity> {

    private EntityManager entityManager;
    @Inject
    private OrderDetailMapper orderDetailMapper;
    @Inject
    private OrderDetailDAO orderDetailDAO;
    @Override
    public ReturnOrderDTO toDTO(ReturnOrderEntity entity) {
        ReturnOrderDTO dto = new ReturnOrderDTO();
        dto.setId(entity.getId());
        dto.setReason(entity.getReason());
        dto.setReturnDate(entity.getReturnDate());
        dto.setStatus(entity.getStatus());
        dto.setOrderDetailId(entity.getOrderDetail().getId());
        dto.setQuantityReturn(entity.getQuantityReturn());
        return dto;
    }

    @Override
    public ReturnOrderEntity toEntity(ReturnOrderDTO dto) {
        ReturnOrderEntity entity = new ReturnOrderEntity();
        entity.setId(dto.getId());
        entity.setReason(dto.getReason());
        entity.setReturnDate(dto.getReturnDate());
        entity.setStatus(dto.getStatus());
        entity.setQuantityReturn(dto.getQuantityReturn());
        return entity;
    }

    @Override
    public List<ReturnOrderDTO> toDTOList(List<ReturnOrderEntity> returnOrderEntities) {
        return GenericMapper.super.toDTOList(returnOrderEntities);
    }

    @Override
    public List<ReturnOrderEntity> toEntityList(List<ReturnOrderDTO> returnOrderDTOS) {
        return GenericMapper.super.toEntityList(returnOrderDTOS);
    }
}
