<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>

    .product__details__tab__content__image img {
        max-width: 100%;
        height: auto;
        border: 1px solid #ddd;
        border-radius: 5px;
        padding: 10px;
    }

    /* CSS cho radio button khi ở trạng thái disabled */
    input[type="radio"]:disabled + label {
        opacity: 0.5;  /* Giảm độ trong suốt */
        pointer-events: none; /* Ngăn không cho người dùng tương tác */
        color: gray; /* Thay đổi màu chữ để biểu thị trạng thái không khả dụng */
    }

    .btn-check:disabled + .btn {
        opacity: 0.5; /* Giảm độ trong suốt cho button */
        cursor: not-allowed; /* Thay đổi con trỏ chuột khi hover */
    }


    .size-options label {
        padding: 10px 15px;
        margin: 0;
        border: 1px solid #ddd;
        cursor: pointer;
        transition: background-color 0.3s ease;
        font-weight: bold; /* Đặt màu chữ đậm */
    }

    .size-options input[type="radio"] {
        display: none;
    }

    .size-options input[type="radio"]:checked + label {
        background-color: #0e0d0d;
        color: #fff;
        font-weight: bold; /* Làm chữ đậm */
    }

    .size-options label:not(:last-child) {
        border-right: none;
    }

</style>

<!-- Shop Details Section Begin -->
<section class="shop-details">
    <div class="product__details__pic">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="product__details__breadcrumb">
                        <a href="<c:url value="/trang-chu"/>">Home</a>
                        <a href="<c:url value="/san-pham"/>">Shop</a>
                        <span>Product Details</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="product__details__content">
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-8">
                    <div class="product__details__text">
                        <h4>Sản phẩm không tồn tại !</h4>
                        <p>Eleven Shop</p>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>