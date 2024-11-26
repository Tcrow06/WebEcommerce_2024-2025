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
                        <div class="col-md-6 col-sm-12 col-xs-12">
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
                            <div class="text-danger small mt-2" id="rule-product-discount">
                                * Những chương trình giảm giá đang diễn ra không cho phép chỉnh sửa.
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
                            <div id="isDiscountProduct" style="font-size: 12px; color: red"></div>
                            <span id = "is-going-on-discount" class="badge bg-success">Đang diễn ra</span><br>
                            <hr />
                            <h3 class="price-container">
                                $129.54
                            </h3>
                            <hr />
                            <form>
                                <input type="hidden" id="id-product-discount">
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
                                    <button type="button" id="cancel-button" class="btn btn-secondary">Dừng giảm giá</button>
                                </div>

                                <div class="mt-3 d-flex justify-content-end">
                                    <button type="button" id="update-button" class="btn btn-primary">Cập nhật</button>
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
                                <th scope="col">Có giảm giá</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Hãng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${model.resultList}">
                                <tr>
                                    <td>
                                        <input type="radio" name="product" value="${item.id}"
                                               data-photo="${item.photo}" data-id="${item.id}"
                                               data-name="${item.name}" data-price="${item.getDiscountedPrice()}"
                                               data-brand="${item.brand}"
                                        <c:if test="${item.productDiscount != null}">
                                                data-productdiscountid="${item.productDiscount.id}"
                                               data-productdiscountname="${item.productDiscount.name}"
                                               data-discountstartdate="${item.productDiscount.startDate}"
                                               data-discountenddate="${item.productDiscount.endDate}"
                                               data-discountpercentage="${item.productDiscount.discountPercentage}"
                                               data-isOutStanding = ${item.productDiscount.isOutStanding}
                                        </c:if>
                                        >
                                    </td>
                                    <td>

                                        <div class="d-flex align-items-center">
                                            <img
                                                    src="<c:url value='/api-image?path=${item.photo}'/>"
                                                    alt="th"
                                                    style="width: 45px; height: 45px"
                                                    class="rounded-circle"
                                            />
                                            <div class="flex-column ms-4">
                                                <c:if test="${item.productDiscount != null}">
                                                    <span class="badge bg-${item.productDiscount.getBootstrapClassStatus ()}">${item.productDiscount.getStatus()}</span>
                                                </c:if>
                                                <p class="mb-0">${item.name}</p>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <c:if test="${item.productDiscount != null}">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckCheckedDisabled_${item.productDiscount.id}" checked disabled/>
                                            <label class="form-check-label" for="flexCheckCheckedDisabled_${item.productDiscount.id}">Giảm_${item.productDiscount.discountPercentage}%</label>
                                        </div>
                                        </c:if>
                                        <c:if test="${item.productDiscount == null}">
                                            <div class="flex-column ms-4">
                                                <p class="mb-2">Không giảm</p>
                                            </div>
                                        </c:if>
                                    </td>
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

                    // Kiểm tra nếu sản phẩm có discount
                    const discountName = selectedProduct.data('productdiscountname');
                    const discountStartDate = selectedProduct.data('discountstartdate');
                    const discountEndDate = selectedProduct.data('discountenddate');
                    const discountPercentage = selectedProduct.data('discountpercentage');
                    const isOutStanding = selectedProduct.data('isoutstanding');
                    const discountId = selectedProduct.data('productdiscountid')

                    console.log(discountId)

                    if (discountName && discountStartDate && discountEndDate && discountPercentage !== undefined) {
                        // Gán giá trị cho các ô input tương ứng
                        $('#discountName').val(discountName);
                        $('#startTime-discount').val(discountStartDate);
                        $('#endTime-discount').val(discountEndDate);
                        $('#discountPercentage').val(discountPercentage);
                        $('#isOutstanding').prop('checked', isOutStanding === true);
                        $('#cancel-button').show()
                        $('#isDiscountProduct').text("");
                        $('#id-product-discount').val(discountId)
                        $('#submit-button').hide()

                        $('#is-going-on-discount').show()

                        const discountStart = new Date(discountStartDate);
                        const discountEnd = new Date(discountEndDate);
                        const currentdate = new Date();

                        // If the start date is before the current date, show "Đang diễn ra"
                        if (discountStart <= currentdate) {
                            $('#is-going-on-discount').removeClass("bg-danger");
                            $('#is-going-on-discount').addClass("bg-success");
                            $('#is-going-on-discount').text("Đang diễn ra");
                            $('#update-button').hide()
                            $('#rule-product-discount').text("* Những chương trình giảm giá đang diễn ra không cho phép chỉnh sửa.")
                        } else {
                            $('#is-going-on-discount').removeClass("bg-success");
                            $('#is-going-on-discount').addClass("bg-danger");
                            $('#is-going-on-discount').text("Sắp diễn ra");
                            $('#update-button').show()
                            $('#rule-product-discount').text("")
                        }
                    } else {
                        $('#submit-button').show()
                        $('#update-button').hide()
                        $('#cancel-button').hide()
                        // Xóa các giá trị nếu sản phẩm không có discount
                        $('#discountName').val('');
                        $('#startTime-discount').val('');
                        $('#endTime-discount').val('');
                        $('#discountPercentage').val('');
                        $('#isOutstanding').prop('checked', false);
                        $('#isDiscountProduct').text("Sản phẩm này chưa được thiết lập giảm giá !");
                        $('#id-product-discount').val(undefined)
                        $('#is-going-on-discount').hide()
                    }

                    // Hiển thị phần tử .product-content
                    $('.main-product').show();

                    // Đóng modal
                    $('#productModal').modal('hide');

                    // Đảm bảo body có thể cuộn lại sau khi modal đóng
                    $('body').removeClass('modal-open').css('overflow', 'auto');
                } else {
                    alert("Vui lòng chọn sản phẩm để xác nhận.");
                }
            });

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

            $('#cancel-button').click(function (){
                showConfirmationModal().then((result) => {
                    if (!result) {
                        console.log("User cancelled the action.");
                        return;
                    }

                    const productDiscountId = $('#id-product-discount').val();

                    if (productDiscountId == undefined) return

                    const data = {
                        id: productDiscountId
                    };
                    sendAPI(data, 'DELETE')
                });
            })


            $('#submit-button').click(function () {
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

    <script>
        $(document).ready(function () {
            // Lắng nghe sự kiện khi người dùng nhập vào ô tìm kiếm
            $('#productSearch').on('input', function () {
                var searchValue = $(this).val().toLowerCase(); // Lấy giá trị tìm kiếm và chuyển về chữ thường

                // Lọc các dòng trong bảng theo tên sản phẩm
                $('#productModal table tbody tr').each(function () {
                    var productName = $(this).find('td:nth-child(2)').text().toLowerCase(); // Lấy tên sản phẩm trong cột thứ 2 (Sản Phẩm)

                    // Kiểm tra nếu tên sản phẩm chứa từ khóa tìm kiếm
                    if (productName.indexOf(searchValue) > -1) {
                        $(this).show(); // Hiển thị dòng nếu tìm thấy
                    } else {
                        $(this).hide(); // Ẩn dòng nếu không tìm thấy
                    }
                });
            });

            // Optional: Reset lại khi đóng modal
            $('#productModal').on('hidden.bs.modal', function () {
                $('#productSearch').val('');  // Xóa giá trị tìm kiếm khi modal đóng
                $('#productModal table tbody tr').show(); // Hiển thị lại tất cả các dòng
            });
        });
    </script>

</div>
