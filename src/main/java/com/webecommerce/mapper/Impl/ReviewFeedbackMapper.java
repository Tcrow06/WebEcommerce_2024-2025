package com.webecommerce.mapper.Impl;

import com.webecommerce.dto.review.ReviewFeedBackDTO;
import com.webecommerce.entity.review.ProductReviewEntity;
import com.webecommerce.entity.review.ReviewFeedbackEntity;
import com.webecommerce.mapper.GenericMapper;

public class ReviewFeedbackMapper implements GenericMapper<ReviewFeedBackDTO, ReviewFeedbackEntity> {


    @Override
    public ReviewFeedBackDTO toDTO(ReviewFeedbackEntity entity) {
        if (entity == null) {
            return null;
        }

        ReviewFeedBackDTO dto = new ReviewFeedBackDTO();
        dto.setContent(entity.getContent());
        dto.setCreateDate(entity.getDate());
        dto.setId(entity.getId());

        return dto;
    }

    @Override
    public ReviewFeedbackEntity toEntity(ReviewFeedBackDTO dto) {

        if (dto == null) {
            return null;
        }

        ReviewFeedbackEntity entity = new ReviewFeedbackEntity();

        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setDate(dto.getCreateDate());

        return entity;
    }
}
