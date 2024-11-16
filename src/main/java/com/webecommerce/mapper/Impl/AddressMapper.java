package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.AddressDTO;
import com.webecommerce.entity.other.AddressEntity;
import com.webecommerce.mapper.GenericMapper;

public class AddressMapper implements GenericMapper<AddressDTO, AddressEntity> {
    @Override
    public AddressDTO toDTO(AddressEntity addressEntity) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(addressEntity.getId());
        addressDTO.setConcrete(addressEntity.getConcrete());
        addressDTO.setCommune(addressEntity.getCommune());
        addressDTO.setDistrict(addressEntity.getDistrict());
        addressDTO.setCity(addressEntity.getCity());
        return addressDTO;
    }

    @Override
    public AddressEntity toEntity(AddressDTO addressDTO) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressDTO.getId() == null ? null : addressDTO.getId());
        addressEntity.setConcrete(addressDTO.getConcrete());
        addressEntity.setCommune(addressDTO.getCommune());
        addressEntity.setDistrict(addressDTO.getDistrict());
        addressEntity.setCity(addressDTO.getCity());
        return addressEntity;
    }
}
