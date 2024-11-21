package com.webecommerce.service.impl;

import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.mapper.GenericMapper;
import com.webecommerce.service.IBillDiscountService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDiscountService implements IBillDiscountService {

    @Inject
    IBillDiscountDAO billDiscountDAO;

    @Inject
    GenericMapper <BillDiscountDTO, BillDiscountEntity> billDiscountMapper;


    @Transactional
    public BillDiscountDTO save (BillDiscountDTO billDiscountDTO) {
        BillDiscountEntity billDiscount = billDiscountMapper.toEntity(billDiscountDTO);

        return billDiscountMapper.toDTO(
                billDiscountDAO.insert(billDiscount));
    }

    @Transactional
    public BillDiscountDTO update (BillDiscountDTO billDiscountDTO) {
        BillDiscountEntity billDiscountEntity = billDiscountDAO.findById(billDiscountDTO.getId());
        if (billDiscountEntity != null) {
            if (billDiscountEntity.getEndDate().isAfter(LocalDateTime.now()) && billDiscountEntity.getStartDate().isAfter(LocalDateTime.now())) {
                // chỉ cho phép cập nhật những discount chưa diễn ra
                billDiscountEntity.setName(billDiscountDTO.getName());
                billDiscountEntity.setStartDate(billDiscountDTO.getStartDate());
                billDiscountEntity.setEndDate(billDiscountDTO.getEndDate());
                billDiscountEntity.setDiscountPercentage(billDiscountDTO.getDiscountPercentage());
                billDiscountEntity.setOutStanding(billDiscountDTO.getIsOutStanding());

                billDiscountEntity.setMinimumInvoiceAmount(billDiscountDTO.getMinimumInvoiceAmount());
                billDiscountEntity.setLoyaltyPointsRequired(billDiscountDTO.getLoyaltyPointsRequired());
                billDiscountEntity.setCode(billDiscountDTO.getCode());
                billDiscountEntity.setMaximumAmount(billDiscountDTO.getMaximumAmount());

                return billDiscountMapper.toDTO(
                        billDiscountDAO.update(billDiscountEntity)
                );
            }
        }

        return null;
    }

    @Transactional
    public BillDiscountDTO cancelProductDiscount(Long id) {
        BillDiscountEntity billDiscountEntity = billDiscountDAO.findById(id);

        if (billDiscountEntity != null) {

            if (billDiscountEntity.getEndDate().isAfter(LocalDateTime.now())) {
                // chỉ cho phép hủy những discount đang diễn ra và chưa diễn ra
                billDiscountEntity.setEndDate(LocalDateTime.now().minusMinutes(1));
                return billDiscountMapper.toDTO(
                        billDiscountDAO.update(billDiscountEntity)
                );
            }
        }

        return null;
    }

    public BillDiscountDTO findById(Long id) {
        return billDiscountMapper.toDTO(billDiscountDAO.findById(id));
    }

    public List<BillDiscountDTO> findAll() {
        List<BillDiscountEntity> list = billDiscountDAO.findAll();
        return billDiscountMapper.toDTOList(list);
    }

    @Override
    public List<BillDiscountDTO> getAllDiscountEligible(Long idUser) {
        List<BillDiscountEntity> list = billDiscountDAO.getAllDiscountEligible(idUser);
        return billDiscountMapper.toDTOList(list);
    }

    public List<BillDiscountDTO> findAllOutStanding () {
        List<BillDiscountEntity> billDiscountEntities = billDiscountDAO.findBillDiscountOutStandingAndStillValid();
        if (billDiscountEntities == null)
            return new ArrayList<>();
        return billDiscountMapper.toDTOList(billDiscountEntities);
    }

    @Override
    public BillDiscountDTO findBillDiscountByCode(String code) {
        return billDiscountMapper.toDTO(billDiscountDAO.findBillDiscountByCode(code));
    }

    @Override
    public BillDiscountDTO findBillDiscountByCodeAndValid(String code) {
        return billDiscountMapper.toDTO(billDiscountDAO.findBillDiscountByCodeAndValid(code));
    }
    public List <BillDiscountDTO> findBillDiscountUpComming () {
        return billDiscountMapper.toDTOList(billDiscountDAO.findBillDiscountUpComming());
    }

    public List <BillDiscountDTO> findExpiredBillDiscount () {
        return billDiscountMapper.toDTOList(billDiscountDAO.findExpiredBillDiscount());
    }

    public List <BillDiscountDTO> findBillDiscountValid () {
        return billDiscountMapper.toDTOList(billDiscountDAO.findBillDiscountValid());
    }


}
