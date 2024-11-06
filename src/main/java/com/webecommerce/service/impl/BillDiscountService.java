package com.webecommerce.service.impl;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.service.IBillDiscountService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

public class BillDiscountService implements IBillDiscountService {

    @Inject
    IBillDiscountDAO billDiscountDAO;

    @Inject
    GenericMapper <BillDiscountDTO, BillDiscountEntity> billDiscountMapper;


    @Transactional
    public BillDiscountDTO save(BillDiscountDTO billDiscountDTO) {
        BillDiscountEntity billDiscount = billDiscountMapper.toEntity(billDiscountDTO);
        return billDiscountMapper.toDTO(
                billDiscountDAO.insert(billDiscount));
    }

    public List<BillDiscountDTO> findAll() {
        List<BillDiscountEntity> list = billDiscountDAO.findAll();
        return billDiscountMapper.toDTOList(list);
    }
}
