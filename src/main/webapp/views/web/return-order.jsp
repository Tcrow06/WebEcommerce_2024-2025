<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    /* Custom styling for the editor */
    .description-editor-container {
        margin: 20px 0;
    }

    /* Style for the outer frame */
    .section-wrapper {
        border: 2px solid #ddd; /* Viền ngoài */
        padding: 20px;
        margin: 20px 0;
        border-radius: 8px;
        background-color: #f9f9f9; /* Màu nền nhẹ */
    }

    textarea[readonly] {
        background-color: #f1f1f1; /* Đổi màu nền khi ô input không thể sửa */
    }
</style>

<link rel="stylesheet" href="/static/web/css/return-order.css" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-blog set-bg" data-setbg="/static/web/img/bg-return-order.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h2>Hoàn trả đơn hàng</h2>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<section class="product-info-section">
    <div class="container">
        <div class="row">
            <c:forEach var="product" items="${productList}">
                <!-- Outer frame for each product entry -->
                <div class="section-wrapper">
                    <div class="row">
                        <!-- Product Display Section -->
                        <div class="col-lg-4 col-md-12">
                            <div class="product-display-section">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg" data-setbg="<c:url value='/api-image?path=${product.imageUrl}'/>">
                                        <!-- Product image (background image set in CSS) -->
                                    </div>
                                    <div class="product__item__text">
                                        <h5 class="product__name">Tên sản phẩm: ${product.productName}</h5>
                                        <p class="product__size">Size: <span>${product.size}</span></p>
                                        <p class="product__color">Màu: <span>${product.color}</span></p>
                                        <p class="product__quantity">Số lượng: <span>${product.quantity}</span></p>
                                        <p class="product__price">Giá: <span>${product.price}</span></p>
                                        <p class="product__id" style="display: none"><span>${product.id}</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Reason Return Order Section -->
                        <div class="col-lg-8 col-md-12">
                            <div class="product-description-section">
                                <br><br>
                                <h4>Lý do hoàn trả</h4>
                                <div>
                                    <label><input type="radio" name="returnReason_${product.id}" value="Không vừa size"> Không vừa size</label><br>
                                    <label><input type="radio" name="returnReason_${product.id}" value="Màu sắc không giống như hình"> Màu sắc không giống như hình</label><br>
                                    <label><input type="radio" name="returnReason_${product.id}" value="Chất liệu không như mong đợi"> Chất liệu không như mong đợi</label><br>
                                    <label><input type="radio" name="returnReason_${product.id}" value="Khác"> Khác</label><br>
                                </div>
                                <!-- Textarea for input instead of Quill editor -->
                                <div class="description-editor-container">
                                    <textarea name="description_${product.id}" placeholder="Nêu vấn đề của bạn tại đây..." rows="16" cols="70" readonly></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div style="text-align: center;">
            <button id="submit-button" class="primary-btn">Xác nhận</button>
        </div>
        <br>
    </div>
</section>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Sử dụng event delegation để lắng nghe thay đổi của tất cả radio button
        document.querySelector('.product-info-section').addEventListener('change', function(event) {
            // Kiểm tra nếu mục thay đổi là radio button
            if (event.target.matches('input[type="radio"]')) {
                const radio = event.target;

                const productId = radio.name.split('_')[1];

                const textarea = radio.closest('.section-wrapper').querySelector('textarea[name="description_' + productId + '"]');

                if (textarea) {
                    if (radio.value === 'Khác') {
                        textarea.readOnly = false;
                        textarea.value = '';
                        textarea.placeholder = "Nêu vấn đề của bạn tại đây...";
                    } else {
                        textarea.readOnly = true;
                        textarea.value = radio.value;
                    }
                } else {
                    console.error("Textarea không tìm thấy cho product ID:", productId);
                }
            }
        });
    });

</script>

<script>
    $('#submit-button').click(function () {
        const returnOrders = [];
        $('.section-wrapper').each(function () {
            const section = $(this);

            const orderDetailId = section.find('.product__id span').text();
            let reason = section.find('textarea[name="description_' + orderDetailId + '"]').val();
            if (!reason) {
                reason = "Không có lý do";
            }
            const status = 0;
            const quantityReturn = section.find('.product__quantity span').text();
            const today = new Date();
            const returnDate = today.toISOString().split('T')[0];
            returnOrders.push({
                orderDetailId: orderDetailId,
                reason: reason,
                returnDate: returnDate,
                status: status,
                quantityReturn: quantityReturn
            });
        });

        const data = {
            resultList: returnOrders,
        };
        $.ajax({
            url: '/api-return-order',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                Swal.fire({
                    icon: 'success',
                    title: 'Thành công!',
                    text: 'Đã gửi yêu cầu trả hàng thành công!',
                    confirmButtonText: 'OK'
                }).then(() => {
                    window.location.href = '/trang-chu/don-hang';
                });
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi!',
                    text: 'Đã xảy ra lỗi: ' + error,
                    confirmButtonText: 'OK'
                });
            }
        });
    });

</script>
