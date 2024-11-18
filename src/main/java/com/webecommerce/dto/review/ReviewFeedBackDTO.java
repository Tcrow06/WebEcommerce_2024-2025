package com.webecommerce.dto.review;

import com.webecommerce.dto.BaseDTO;

import java.time.LocalDateTime;

public class ReviewFeedBackDTO extends BaseDTO <ReviewFeedBackDTO> {

    private Long id;

    private String content;

    private LocalDateTime createDate;

}
