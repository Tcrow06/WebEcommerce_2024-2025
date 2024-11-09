package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.discount.DiscountDTO;
import com.webecommerce.entity.discount.DiscountEntity;

public class DiscountMapper {

    public DiscountDTO toDTO(DiscountEntity discountEntity, DiscountDTO discountDTO) {
        if (discountEntity == null)
            return null;

        discountDTO.setId(discountEntity.getId());
        discountDTO.setName(discountEntity.getName());
        discountDTO.setStartDate(discountEntity.getStartDate());
        discountDTO.setEndDate(discountEntity.getEndDate());
        discountDTO.setDiscountPercentage(discountEntity.getDiscountPercentage());
        discountDTO.setOutStanding(discountEntity.isOutStanding());

        return discountDTO;
    }

    public DiscountEntity toEntity(DiscountDTO discountDTO, DiscountEntity discountEntity) {
        if (discountDTO == null)
            return null;

        discountEntity.setId(discountDTO.getId());
        discountEntity.setName(discountDTO.getName());
        discountEntity.setStartDate(discountDTO.getStartDate());
        discountEntity.setEndDate(discountDTO.getEndDate());
        discountEntity.setDiscountPercentage(discountDTO.getDiscountPercentage());
        discountEntity.setOutStanding(discountDTO.getIsOutStanding());

        return discountEntity;
    }
}
