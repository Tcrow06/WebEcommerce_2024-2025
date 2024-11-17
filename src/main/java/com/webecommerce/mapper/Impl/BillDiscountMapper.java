package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.mapper.GenericMapper;

public class BillDiscountMapper extends DiscountMapper implements GenericMapper<BillDiscountDTO, BillDiscountEntity> {

    @Override
    public BillDiscountDTO toDTO(BillDiscountEntity billDiscountEntity) {
        BillDiscountDTO billDiscountDTO = new BillDiscountDTO();

        if (super.toDTO(billDiscountEntity,billDiscountDTO) == null)
            return null;

        billDiscountDTO.setMinimumInvoiceAmount(billDiscountEntity.getMinimumInvoiceAmount());
        billDiscountDTO.setLoyaltyPointsRequired(billDiscountEntity.getLoyaltyPointsRequired());
        billDiscountDTO.setCode(billDiscountEntity.getCode());
        billDiscountDTO.setMaximumAmount(billDiscountEntity.getMaximumAmount());

        return billDiscountDTO;
    }

    @Override
    public BillDiscountEntity toEntity(BillDiscountDTO billDiscountDTO) {
        BillDiscountEntity billDiscountEntity = new BillDiscountEntity();

        if (super.toEntity(billDiscountDTO,billDiscountEntity) == null)
            return null;

        billDiscountEntity.setMinimumInvoiceAmount(billDiscountDTO.getMinimumInvoiceAmount());
        billDiscountEntity.setLoyaltyPointsRequired(billDiscountDTO.getLoyaltyPointsRequired());
        billDiscountEntity.setCode(billDiscountDTO.getCode());
        billDiscountEntity.setMaximumAmount(billDiscountDTO.getMaximumAmount());

        return billDiscountEntity;
    }
}
