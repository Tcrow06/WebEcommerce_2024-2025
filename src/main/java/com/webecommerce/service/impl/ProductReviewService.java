package com.webecommerce.service.impl;

import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.order.IOrderDetailDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dao.review.IProductReviewDAO;
import com.webecommerce.dao.review.IReviewFeedbackDAO;
import com.webecommerce.dto.review.ProductReviewDTO;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.product.ProductEntity;
import com.webecommerce.entity.review.ProductReviewEntity;
import com.webecommerce.entity.review.ReviewFeedbackEntity;
import com.webecommerce.mapper.Impl.ProductReviewMapper;
import com.webecommerce.mapper.Impl.ReviewFeedbackMapper;
import com.webecommerce.service.IProductReviewService;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductReviewService implements IProductReviewService {


    @Inject
    private IProductReviewDAO productReviewDAO;

    @Inject
    private ReviewFeedbackMapper reviewFeedbackMapper; ;

    @Inject
    private ICustomerDAO customerDAO;

    @Inject
    private IOrderDetailDAO orderDetailDAO;

    @Inject
    private ProductReviewMapper productReviewMapper;

    @Inject
    private IReviewFeedbackDAO reviewFeedbackDAO;


    public ProductReviewDTO findByOrderDetailId(Long OrderDetailId) {
        ProductReviewEntity productReviewEntity = productReviewDAO.getProductReviewByOrderDetailId(OrderDetailId);

        if (productReviewEntity != null) {
            return productReviewMapper.toDTO(productReviewEntity);
        }
        return null;
    }

    public ProductReviewDTO save (ProductReviewDTO productReview) {

        ProductReviewEntity productReviewEntity = productReviewDAO.getProductReviewByOrderDetailId(productReview.getOrderDetail().getId());

        if (productReviewEntity != null) {
            return null ;
        }

        productReviewEntity = productReviewMapper.toEntity(productReview);

        productReviewEntity.setDate(LocalDateTime.now());

        CustomerEntity customerEntity = customerDAO.findById(productReview.getCustomerId());
        OrderDetailEntity orderDetailEntity = orderDetailDAO.findById(productReview.getOrderDetail().getId());


        if (customerEntity != null && orderDetailEntity != null) {
            productReviewEntity.setCustomer(customerEntity);
            productReviewEntity.setOrderDetail(orderDetailEntity);

            customerEntity.getProductReviews().add(productReviewEntity);
            orderDetailEntity.getProductReviews().add(productReviewEntity);

            return productReviewMapper.toDTO(productReviewDAO.insert(productReviewEntity));
        }
        return null;
    }

    public List<ProductReviewDTO> getProductReviewByProductId(Long productId) {
        List <ProductReviewEntity> productReviewEntities = productReviewDAO.getProductReviewByProduct(productId);


        List <ProductReviewDTO> productReviewDTOS = new ArrayList<>();

        if (productReviewEntities != null) {
            for(ProductReviewEntity  pre: productReviewEntities){
                ReviewFeedbackEntity reviewFeedbackEntity = reviewFeedbackDAO.findReviewFeedbackByProductReviewId(pre.getId());
                pre.setReviewFeedback(reviewFeedbackEntity);
            }
            for (ProductReviewEntity productReviewEntity : productReviewEntities) {
                ProductReviewDTO productReviewDTO = productReviewMapper.toDTO(productReviewEntity);
                // lấy reviewfeedback nếu có
                if (productReviewEntity.getReviewFeedback() != null) {
                    productReviewDTO.setReviewFeedback(
                            reviewFeedbackMapper.toDTO(productReviewEntity.getReviewFeedback())
                    );
                }
                productReviewDTOS.add(productReviewDTO);
            }
        }

        return productReviewDTOS;
    }
}
