<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value='/static/admin/add-product/style.css'/>">
<style>
    .modal-open {
        overflow: auto !important;  /* Đảm bảo body có thể cuộn lại sau khi modal đóng */
    }
</style>

<%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> --%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Thêm giảm giá cho sản phẩm</h4>
            <h6>Marketing</h6>
        </div>
    </div>

    <div class="container mt-4">
        <!-- Title -->
        <h2>Tạo Chương Trình Khuyến Mãi mới</h2>

        <!-- Basic Information -->
        <div class="card mt-4 main-product" style="display: none;">

            <%--  input id product          --%>
            <input type="hidden" id="id-productselected" value="">

            <div class="card-body">
                <h5 class="card-title">Thông tin cơ bản</h5>

                <div class="product-content product-wrap clearfix product-deatil">
                    <div class="row">
                        <div class="col-md-5 col-sm-12 col-xs-12">
                            <div class="product-image">
                                <div id="myCarousel-2" class="carousel slide">
                                    <div class="carousel-inner">
                                        <!-- Slide 1 -->
                                        <div class="carousel-item active">
                                            <img src="https://www.bootdey.com/image/700x400/FFB6C1/000000" class="img-responsive" alt="" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6 col-md-offset-1 col-sm-12 col-xs-12">
                            <h2 class="name">
                                Product Name Title Here
                                <small>Product by <a href="javascript:void(0);">Adeline</a></small>
                                <i class="fa fa-star fa-2x text-primary"></i>
                                <i class="fa fa-star fa-2x text-primary"></i>
                                <i class="fa fa-star fa-2x text-primary"></i>
                                <i class="fa fa-star fa-2x text-primary"></i>
                                <i class="fa fa-star fa-2x text-muted"></i>
                                <span class="fa fa-2x"><h5>(109) Votes</h5></span>
                                <a href="javascript:void(0);">109 customer reviews</a>
                            </h2>
                            <hr />
                            <h3 class="price-container">
                                $129.54
                            </h3>
                            <hr />
                            <form>
                                <div class="mb-3">
                                    <label for="discountName" class="form-label">Tên chương trình khuyến mãi</label>
                                    <input type="text" class="form-control" id="discountName" placeholder="Nhập tên chương trình khuyến mãi" maxlength="150">
                                    <div class="error-message" id="discountNameError" style="font-size: 12px; color: red"></div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Thời gian khuyến mãi</label>
                                    <div class="input-group">
                                        <input type="datetime-local" class="form-control" id="startTime-discount">
                                        <span class="input-group-text">to</span>
                                        <input type="datetime-local" class="form-control" id="endTime-discount">
                                    </div>
                                    <div class="error-message" id="dateInput" style="font-size: 12px; color: red"></div>
                                </div>

                                <div class="mb-3">
                                    <label for="discountPercentage" class="form-label">Phần trăm giảm giá (%)</label>
                                    <input type="number" class="form-control" id="discountPercentage" placeholder="Nhập phần trăm giảm giá" min="0" max="100">
                                </div>

                                <!-- Outstanding Checkbox -->
                                <div class="form-check mb-3">
                                    <input class="form-check-input" type="checkbox" id="isOutstanding">
                                    <label class="form-check-label" for="isOutstanding">Nổi bật mã giảm giá này</label>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>

                <!-- Confirm and Cancel Buttons -->
                <div class="mt-3 d-flex justify-content-end">
                    <button id="submit-button" class="btn btn-primary">Xác nhận</button>
                </div>

            </div>
        </div>

        <!-- Promotion Products -->
        <div class="card mt-4">
            <div class="card-body">
                <h5 class="card-title">Sản phẩm khuyến mãi</h5>
                <button class="btn btn-outline-primary" data-toggle="modal" data-target="#productModal">+ Chọn sản phẩm</button>
            </div>
        </div>
    </div>

    <!-- Product Modal -->
    <div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="productModalLabel">Chọn Sản Phẩm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="productSearch" class="form-label">Tìm</label>
                        <input type="text" class="form-control" id="productSearch" placeholder="Tên sản phẩm">
                    </div>
                    <!-- Container for table with fixed height to enable scrolling -->
                    <div style="max-height: 300px; overflow-y: auto;">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Chọn</th>
                                <th scope="col">Sản Phẩm</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Hãng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${model.resultList}">
                                <tr>
                                    <td><input type="radio" name="product" value="${item.id}"
                                               data-photo="${item.photo}" data-id="${item.id}"
                                               data-name="${item.name}" data-price="${item.price}"
                                               data-brand="${item.brand}"></td>
                                    <td>${item.name}</td>
                                    <td>${item.price}</td>
                                    <td>${item.brand}</td>
                                </tr>
                            </c:forEach>

                            <!-- Additional rows can be added here -->
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    <button type="button" class="btn btn-primary">Xác nhận</button>
                </div>
            </div>
        </div>
    </div>



    <script>
        $(document).ready(function () {
            // Khi nhấn nút "Xác nhận" trong modal
            $('#productModal .btn-primary').click(function () {
                // Lấy thông tin sản phẩm đã chọn
                const selectedProduct = $('input[name="product"]:checked');

                // Kiểm tra nếu có sản phẩm được chọn
                if (selectedProduct.length > 0) {
                    // Lấy các thuộc tính dữ liệu từ sản phẩm đã chọn
                    const productId = selectedProduct.data('id');
                    const productName = selectedProduct.data('name');
                    const productPrice = selectedProduct.data('price');
                    const productBrand = selectedProduct.data('brand');
                    const productPhoto = selectedProduct.data('photo');

                    $('#id-productselected').val(productId)

                    // Cập nhật thông tin sản phẩm trong phần .product-content
                    $('.product-content .name').html(
                        productName + ` <small>Product by ` + productBrand + `</small>`
                    );
                    $('.product-content .price-container').html(`$` + productPrice + ` <small>*price</small>`);
                    $('.product-content .product-image img').attr('src', "<c:url value='/api-image?path=' />" + productPhoto);

                    // Hiển thị phần tử .product-content
                    $('.main-product').show();

                    // Đóng modal
                    $('#productModal').modal('hide');

                    // Đảm bảo body có thể cuộn lại sau khi modal đóng
                    $('body').removeClass('modal-open').css('overflow', 'auto');
                } else {
                    // Nếu không có sản phẩm được chọn
                    alert("Vui lòng chọn sản phẩm để xác nhận.");
                }
            });

            $('#submit-button').click(function () {
                if (!checkInput()) return

                const name = $('#discountName').val();
                const productId = $('#id-productselected').val();
                const startDate = $('#startTime-discount').val();
                const endDate = $('#endTime-discount').val();
                const discountPercentage = $('#discountPercentage').val();
                const isOutstanding = $('#isOutstanding').is(':checked');

                const data = {
                    name: name,
                    startDate: startDate,
                    endDate: endDate,
                    discountPercentage: discountPercentage,
                    isOutStanding: isOutstanding,
                    product: {
                        id: productId
                    }
                };

                $.ajax({
                    url: '/api-product-discount',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function (response) {
                        alert("Đã gửi thông tin thành công!");
                    },
                    error: function (xhr, status, error) {
                        alert("Lỗi: " + error);
                    }
                });
            });


            function checkInput() {
                const name = $('#discountName').val();
                const startDate = $('#startTime-discount').val();
                const endDate = $('#endTime-discount').val();

                // Kiểm tra nếu name rỗng
                if (!name) {
                    $('#discountNameError').text("Vui lòng nhập tên chương trình khuyến mãi.");
                    return false;
                } else {
                    $('#discountNameError').text(""); // Xóa lỗi nếu đã nhập tên
                }

                // Kiểm tra nếu chưa chọn ngày bắt đầu hoặc ngày kết thúc
                if (!startDate || !endDate) {
                    $('#dateInput').text("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.");
                    return false;
                } else {
                    $('#dateInput').text(""); // Xóa lỗi nếu đã chọn ngày hợp lệ
                }

                // Kiểm tra nếu endDate nhỏ hơn startDate
                if (new Date(endDate) < new Date(startDate)) {
                    $('#dateInput').text("Ngày kết thúc không thể nhỏ hơn ngày bắt đầu.");
                    return false;
                } else {
                    $('#dateInput').text(""); // Xóa lỗi nếu ngày hợp lệ
                }

                return true; // Tất cả hợp lệ
            }


        });

    </script>

</div>
