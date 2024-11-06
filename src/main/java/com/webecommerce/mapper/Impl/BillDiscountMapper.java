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
        billDiscountDTO.setInvoiceType(billDiscountEntity.getInvoiceType());
        billDiscountDTO.setCode(billDiscountEntity.getCode());

        return billDiscountDTO;
    }

    @Override
    public BillDiscountEntity toEntity(BillDiscountDTO billDiscountDTO) {
        BillDiscountEntity billDiscountEntity = new BillDiscountEntity();

        if (super.toEntity(billDiscountDTO,billDiscountEntity) == null)
            return null;

        billDiscountEntity.setMinimumInvoiceAmount(billDiscountDTO.getMinimumInvoiceAmount());
        billDiscountEntity.setInvoiceType(billDiscountDTO.getInvoiceType());
        billDiscountEntity.setCode(billDiscountDTO.getCode());

        return billDiscountEntity;
    }
}
