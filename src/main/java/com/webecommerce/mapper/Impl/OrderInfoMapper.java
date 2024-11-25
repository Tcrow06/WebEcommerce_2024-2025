package com.webecommerce.mapper.Impl;

import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.OrderInfoDTO;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.mapper.GenericMapper;

import javax.inject.Inject;
import java.util.List;

public class OrderInfoMapper implements GenericMapper<OrderInfoDTO, OrderInfoEntity> {
    @Inject
    private AddressMapper addressMapper;

    @Inject
    private ICustomerDAO customerDAO;

    @Override
    public OrderInfoDTO toDTO(OrderInfoEntity entity) {
        if(entity==null){
            return null;
        }
        OrderInfoDTO dto = new OrderInfoDTO();
        dto.setId(entity.getId());
        dto.setAddress(addressMapper.toDTO(entity.getAddress()));
        dto.setPhone(entity.getPhone());
        dto.setRecipient(entity.getRecipient());
        dto.setIsDefault(entity.getIsDefault());
        if(entity.getCustomer()!=null)
            dto.setCustomerId(entity.getCustomer().getId());
        return dto;
    }

    @Override
    public OrderInfoEntity toEntity(OrderInfoDTO orderInfoDTO) {
        if (orderInfoDTO==null){
            return null;
        }
        OrderInfoEntity entity = new OrderInfoEntity();
        entity.setId(orderInfoDTO.getId());
        entity.setAddress(addressMapper.toEntity(orderInfoDTO.getAddress()));
        entity.setPhone(orderInfoDTO.getPhone());
        entity.setRecipient(orderInfoDTO.getRecipient());
        entity.setIsDefault(orderInfoDTO.getIsDefault());
        entity.setCustomer(customerDAO.findById(orderInfoDTO.getCustomerId()));
        return entity;
    }

    @Override
    public List<OrderInfoDTO> toDTOList(List<OrderInfoEntity> orderInfoEntities) {
        return GenericMapper.super.toDTOList(orderInfoEntities);
    }

    @Override
    public List<OrderInfoEntity> toEntityList(List<OrderInfoDTO> orderInfoDTOS) {
        return GenericMapper.super.toEntityList(orderInfoDTOS);
    }
}
