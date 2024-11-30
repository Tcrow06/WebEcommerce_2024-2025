<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value='/static/admin/add-product/style.css'/>">
<style>
    .modal-open {
        overflow: auto !important;  /* Đảm bảo body có thể cuộn lại sau khi modal đóng */
    }
</style>

<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>--%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Thêm giảm giá cho sản phẩm</h4>
            <h6>Marketing</h6>
        </div>
    </div>

    <div class="container mt-4">
        <!-- Title -->
        <h2>Chỉnh sửa Chương Trình Khuyến Mãi</h2>

        <!-- Basic Information -->
        <div class="card mt-4 main-product">

            <%--  input id product          --%>
            <input type="hidden" id="id-productselected" value="${model.product.id}">

            <div class="card-body">
                <h5 class="card-title">Thông tin cơ bản</h5>

                <div class="product-content product-wrap clearfix product-deatil">
                    <div class="row">
                        <div class="col-md-6 col-sm-12 col-xs-12">
                            <div class="product-image">
                                <div id="myCarousel-2" class="carousel slide">
                                    <div class="carousel-inner">
                                        <!-- Slide 1 -->
                                        <div class="carousel-item active">
                                            <img src="<c:url value='/api-image?path=${model.product.photo}'/>" class="img-responsive" alt="" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="text-danger small mt-2" id="rule-product-discount">
                                * Những chương trình giảm giá đang diễn ra không cho phép chỉnh sửa.
                            </div>
                        </div>

                        <input type="hidden" id="idProduct" value="${model.product.id}">

                        <div class="col-md-6 col-md-offset-1 col-sm-12 col-xs-12">
                            <h2 class="name">
                                ${model.product.name}
                                <small>Product by <a href="javascript:void(0);">${model.product.brand}</a></small>
                            </h2>
                            <span id = "is-going-on-discount" class="badge bg-${model.getBootstrapClassStatus()}">${model.getStatus()}</span><br>
                            <hr />
                            <h3 class="price-container">
                                ${model.product.getDiscountedPrice()}
                            </h3>
                            <hr />

                                <c:if test="${model != null}">
                                    <input type="hidden" name="productDiscount" id = "productDiscountSelected"
                                           data-id="${model.id}"
                                           data-name = "${model.name}"
                                           data-startDate="${model.startDate}"
                                           data-endDate="${model.endDate}"
                                           data-discountPercentage="${model.discountPercentage}"
                                           data-isOutStanding="${model.isOutStanding}"
                                           data-status = "${model.getStatus()}"
                                    >
                                </c:if>

                                <input type="hidden" id="id-product-discount" value="${model.id}">
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

                                <!-- Confirm and Cancel Buttons -->
                                <div class="mt-3 d-flex justify-content-end">
                                    <button id="cancel-button" class="btn btn-secondary">Dừng giảm giá</button>
                                </div>

                                <div class="mt-3 d-flex justify-content-end">
                                    <button id="update-button" class="btn btn-primary">Cập nhật</button>
                                </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                    <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn thực hiện ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal" id="cancelButton">Hủy</button>
                    <button type="button" class="btn btn-primary" id="okButton">OK</button>
                </div>
            </div>
        </div>
    </div>



    <script>


        $(document).ready(function () {
            // Lấy dữ liệu từ input hidden với id="productDiscountSelected"
            const productDiscount = $('#productDiscountSelected');

            if (productDiscount.length) {
                // Lấy các giá trị từ thuộc tính data
                const id = productDiscount.data('id');
                const name = productDiscount.data('name');
                const startDate = productDiscount.data('startdate');
                const endDate = productDiscount.data('enddate');
                const discountPercentage = productDiscount.data('discountpercentage');
                const isOutStanding = productDiscount.data('isoutstanding');
                const status = productDiscount.data('status')

                if (status == "đã kết thúc") {
                    $('#update-button').hide();
                    $('#cancel-button').hide();
                }
                if (status == 'đang diễn ra') {
                    $('#update-button').hide();
                }

                // Đẩy dữ liệu vào các input tương ứng
                $('#id-product-discount').val(id);
                $('#discountName').val(name);
                $('#startTime-discount').val(startDate);
                $('#endTime-discount').val(endDate);
                $('#discountPercentage').val(discountPercentage);

                // Nếu isOutStanding là true, check vào ô checkbox
                if (isOutStanding) {
                    $('#isOutstanding').prop('checked', true);
                } else {
                    $('#isOutstanding').prop('checked', false);
                }
            }
        });



        function showConfirmationModal() {
            return new Promise((resolve) => {
                // Hiển thị modal
                $('#exampleModal').modal('show');

                // Khi người dùng nhấn "OK"
                $('#okButton').one('click', function () {
                    resolve(true); // Người dùng đồng ý
                    $('#exampleModal').modal('hide');
                });

                // Khi người dùng nhấn "Cancel"
                $('#cancelButton').one('click', function () {
                    resolve(false); // Người dùng hủy
                    $('#exampleModal').modal('hide');
                });
            });
        }


        $(document).ready(function () {

            $('#cancel-button').click(function (){

                showConfirmationModal().then((result) => {
                    if (!result) {
                        console.log("User cancelled the action.");
                        return; // Người dùng chọn "Cancel", dừng xử lý
                    }

                    const productDiscountId = $('#id-product-discount').val();

                    if (productDiscountId == undefined) return

                    const data = {
                        id: productDiscountId
                    };
                    sendAPI(data, 'DELETE')
                });
            })


            $('#update-button').click(function () {

                if (!checkInput()) return

                showConfirmationModal().then((result) => {
                    if (!result) {
                        console.log("User cancelled the action.");
                        return; // Người dùng chọn "Cancel", dừng xử lý
                    }

                    const id = $('#id-product-discount').val();
                    const name = $('#discountName').val();
                    const productId = $('#id-productselected').val();
                    const startDate = $('#startTime-discount').val();
                    const endDate = $('#endTime-discount').val();
                    const discountPercentage = $('#discountPercentage').val();
                    const isOutstanding = $('#isOutstanding').is(':checked');

                    const data = {
                        id: id,
                        name: name,
                        startDate: startDate,
                        endDate: endDate,
                        discountPercentage: discountPercentage,
                        isOutStanding: isOutstanding,
                        product: {
                            id: productId
                        }
                    };

                    sendAPI(data, 'POST')
                });
            });

            function sendAPI (data,method) {
                $.ajax({
                    url: '/api-product-discount',
                    type: method,
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    beforeSend: function () {
                        // Hiển thị loader trước khi AJAX bắt đầu
                        $('#global-loader').css('display', 'flex');
                    },
                    success: function (response) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Thành công',
                            text: response.toString()
                        }).then(() => {
                            window.location.href = 'giam-gia-cho-san-pham'
                        });
                    },
                    error: function (xhr, status, error) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi',
                            text: error
                        });
                    },complete: function () {
                        $('#global-loader').css('display', 'none');
                    }
                });
            }


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
