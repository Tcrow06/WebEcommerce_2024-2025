package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.AddressDTO;
import com.webecommerce.entity.other.AddressEntity;
import com.webecommerce.mapper.GenericMapper;

import java.util.List;

public class AddressMapper implements GenericMapper<AddressDTO, AddressEntity> {

    @Override
    public AddressDTO toDTO(AddressEntity entity) {
        AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setCity(entity.getCity());
        dto.setCommune(entity.getCommune());
        dto.setConcrete(entity.getConcrete());
        dto.setDistrict(entity.getDistrict());
        return dto;
    }

    @Override
    public AddressEntity toEntity(AddressDTO addressDTO) {
        AddressEntity entity = new AddressEntity();
        entity.setId(addressDTO.getId());
        entity.setCity(addressDTO.getCity());
        entity.setCommune(addressDTO.getCommune());
        entity.setConcrete(addressDTO.getConcrete());
        entity.setDistrict(addressDTO.getDistrict());
        return entity;
    }

    @Override
    public List<AddressDTO> toDTOList(List<AddressEntity> addressEntities) {
        return GenericMapper.super.toDTOList(addressEntities);
    }

    @Override
    public List<AddressEntity> toEntityList(List<AddressDTO> addressDTOS) {
        return GenericMapper.super.toEntityList(addressDTOS);
    }
}
