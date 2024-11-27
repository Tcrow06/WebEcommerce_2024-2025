<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value='/static/admin/add-product/style.css'/> ">

<style>
    .error-message {
        color: red;
        font-size: 12px;
    }
</style>

<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Tạo mã giảm giá cho đơn hàng</h4>
            <h6>Maketing</h6>
            <div class="text-danger small mt-2" id="rule-product-discount">
                * Những chương trình giảm giá đang diễn ra và đã kết thúc không cho phép chỉnh sửa.
            </div>
        </div>
    </div>


    <div class="container mt-5">
        <h2 class="mb-4">Tạo mã giảm giá mới</h2>

        <!-- Basic Info Section -->
        <form>
            <div class="card p-4">
                <h5 class="card-title">Thông tin cơ bản</h5>

                <c:if test="${model != null}">
                    <input type="hidden" name="billDiscount" id = "billDiscountSelected"
                           data-id="${model.id}"
                           data-name = "${model.name}"
                           data-startDate="${model.startDate}"
                           data-endDate="${model.endDate}"
                           data-discountPercentage="${model.discountPercentage}"
                           data-isOutStanding="${model.isOutStanding}"
                           data-minimumInvoiceAmount = "${model.minimumInvoiceAmount}"
                           data-loyaltyPointsRequired = "${model.loyaltyPointsRequired}"
                           data-code = "${model.code}"
                           data-maximumAmount = ${model.maximumAmount}
                    >
                </c:if>

                <!-- Voucher Type -->
                <div class="btn-group btn-group-toggle mb-4" data-toggle="buttons">
                    <label class="btn btn-primary active">
                        Voucher toàn Shop
                    </label>
                </div>

                <input type="hidden" id="id-billdiscount">

                <!-- Discount Program Name -->
                <div class="form-group">
                    <label for="discountProgram">Tên chương trình giảm giá</label>
                    <input type="text" class="form-control" id="discountProgram" placeholder="Tên của chương trình">
                    <small class="form-text text-muted">Tên Voucher</small>
                    <div class="error-message" id="nameError" style="font-size: 12px"></div>
                </div>

                <!-- Voucher Code -->
                <div class="form-group">
                    <label for="voucherCode">Mã voucher</label>
                    <input type="text" class="form-control" id="voucherCode" maxlength="5" placeholder="CPOF DFH">
                    <small class="form-text text-muted">Vui lòng chỉ nhập các kí tự chữ cái (A-Z), số (0-9); tối đa 5 kí tự.</small>
                    <div class="error-message" id="codeError" style="font-size: 12px"></div>
                </div>

                <!-- Usage Time -->
                <div class="form-group">
                    <label>Thời gian sử dụng mã</label>
                    <span style="display: none; margin-bottom: 5px " id = "is-going-on-discount" class="badge bg-success">Đang diễn ra</span><br>
                    <div class="input-group mb-3">
                        <input type="datetime-local" class="form-control" id="startDate">
                        <input type="datetime-local" class="form-control" id="endDate">
                    </div>
                    <div class="error-message" id="dateError" style="font-size: 12px"></div>
                </div>

                <div class="form-group">
                    <label>Người dùng mục tiêu</label>
                    <div class="row">
                        <div class="col">
                            <select class="form-control" id="categorySelect" onchange="toggleCustomScoreInput()">
                                <option value="50">Người dùng có điểm trên 50</option>
                                <option value="100">Người dùng có điểm trên 100</option>
                                <option value="200">Người dùng có điểm trên 200</option>
                                <option value="500">Người dùng có điểm trên 500</option>
                                <option value="custom">Nhập điểm tùy chỉnh</option>
                            </select>
                        </div>
                        <div class="col"></div>
                    </div>
                    <!-- Custom score input, initially hidden -->
                    <div class="row mt-3" id="customScoreInput" style="display: none;">
                        <div class="col">
                            <input type="number" class="form-control" id="customScore" placeholder="Nhập số điểm">
                            <div class="error-message" id="scoreError" style="font-size: 12px"></div>
                        </div>
                        <div class="col"></div>
                    </div>
                </div>

                <!-- Outstanding Checkbox -->
                <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" id="isOutstanding">
                    <label class="form-check-label" for="isOutstanding">Nổi bật mã giảm giá này</label>
                </div>
            </div>

            <div class="container mt-5">
                <h2 class="mb-4">Thiết lập mã giảm giá</h2>

                <!-- Discount Setup Form -->
                <form>
                    <div class="card p-4">

                        <div class="mb-3">
                            <label for="discountPercentage" class="form-label">Phần trăm giảm giá (%)</label>
                            <input type="number" class="form-control" id="discountPercentage" placeholder="Nhập phần trăm giảm giá" min="0" max="100">
                            <div class="error-message" id="precentError" style="font-size: 12px"></div>
                        </div>

                        <!-- Minimum Order Value -->
                        <div class="form-group">
                            <label for="minOrderValue">Giá trị đơn hàng tối thiểu</label>
                            <input type="number" class="form-control" id="minOrderValue" placeholder="Nhập vào">
                            <div class="error-message" id="minOrderValueError" style="font-size: 12px"></div>
                        </div>

                        <!-- Usage Limit -->
                        <div class="form-group">
                            <label for="usageLimit">Số tiền được giảm tối đa</label>
                            <input type="number" class="form-control" id="usageLimit" placeholder="Nhập vào">
                            <small class="form-text text-muted">Số tiền được giảm tối đa</small>
                            <div class="error-message" id="maximumAmountError" style="font-size: 12px"></div>
                        </div>

                    </div>

                </form>
            </div>

            <!-- Buttons -->
            <div class="mt-4">
                <button id="cancel-btn" type="button" class="btn btn-secondary">Dừng Chương trình</button>
                <button id="add-Product" type="submit" class="btn btn-primary">Xác nhận</button>
            </div>
        </form>
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

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

        $(document).ready(function() {

            const $billDiscountSelected = $('#billDiscountSelected');

            if ($billDiscountSelected.length > 0) {
                $('.page-header h4').text('Cập nhật mã giảm giá cho đơn hàng');
                $('.container h2').text('Cập nhật mã giảm giá');


                // Lấy dữ liệu từ các thuộc tính data-
                const id = $billDiscountSelected.data('id');
                const name = $billDiscountSelected.data('name');
                const startDate = $billDiscountSelected.data('startdate');
                const endDate = $billDiscountSelected.data('enddate');
                const discountPercentage = $billDiscountSelected.data('discountpercentage');
                const isOutStanding = $billDiscountSelected.data('isoutstanding');
                const minimumInvoiceAmount = $billDiscountSelected.data('minimuminvoiceamount');
                const loyaltyPointsRequired = $billDiscountSelected.data('loyaltypointsrequired');
                const code = $billDiscountSelected.data('code');
                const maximumAmount = $billDiscountSelected.data('maximumamount');

                const discountStart = new Date(startDate);
                const discountEnd = new Date(endDate);
                const currentdate = new Date();

                if (discountStart < currentdate && discountEnd > currentdate) {
                    $('#is-going-on-discount').removeClass("bg-danger");
                    $('#is-going-on-discount').removeClass("bg-secondary");
                    $('#is-going-on-discount').addClass("bg-success");
                    $('#is-going-on-discount').text("Đang diễn ra");

                } else if (discountStart > currentdate && discountEnd > currentdate) {
                    $('#is-going-on-discount').removeClass("bg-success");
                    $('#is-going-on-discount').removeClass("bg-secondary");
                    $('#is-going-on-discount').addClass("bg-danger");
                    $('#is-going-on-discount').text("Sắp diễn ra");
                } else {
                    $('#is-going-on-discount').removeClass("bg-success");
                    $('#is-going-on-discount').removeClass("bg-danger");
                    $('#is-going-on-discount').addClass("bg-secondary");
                    $('#is-going-on-discount').text("Đã kết thúc");
                }
                $('#is-going-on-discount').show()


                if (discountStart > currentdate && discountEnd > currentdate) { // chỉ chính sửa những discount chưa diễn ra
                    $('#add-Product').text("Cập nhật")
                    $('#add-Product').show()
                }
                else $('#add-Product').hide()
                if (discountEnd < currentdate)
                    $('#cancel-btn').hide()

                // Gán dữ liệu vào các ô input tương ứng
                $('#id-billdiscount').val(id)
                $('#discountProgram').val(name);
                $('#startDate').val(startDate);
                $('#endDate').val(endDate);
                $('#discountPercentage').val(discountPercentage);
                $('#minOrderValue').val(minimumInvoiceAmount);
                $('#voucherCode').val(code);
                $('#usageLimit').val(maximumAmount);

                // Xử lý trường hợp loyaltyPointsRequired
                if ([50, 100, 200, 500].includes(loyaltyPointsRequired)) {
                    $('#categorySelect').val(loyaltyPointsRequired);
                    $('#customScoreInput').hide(); // Ẩn input tùy chỉnh nếu điểm khớp với các giá trị cố định
                } else {
                    $('#categorySelect').val('custom');
                    $('#customScore').val(loyaltyPointsRequired);
                    $('#customScoreInput').show(); // Hiển thị input tùy chỉnh nếu điểm không khớp
                }

                // Xử lý checkbox "Nổi bật"
                $('#isOutstanding').prop('checked', isOutStanding);
            } else $('#cancel-btn').hide()


            // Function to toggle custom score input based on category selection
            $('#categorySelect').change(function() {
                const selectValue = $('#categorySelect').val();
                if (selectValue === "custom") {
                    $('#customScoreInput').show(); // Show custom score input
                } else {
                    $('#customScoreInput').hide(); // Hide custom score input
                }
            });

            // Function to check input validation
            function checkInput() {
                let isValid = true;

                // Reset previous error messages
                $('.error-message').text('');

                // Validate Discount Program Name
                const discountProgram = $('#discountProgram').val();
                if (!discountProgram) {
                    $('#nameError').text('Tên chương trình giảm giá không được để trống.');
                    isValid = false;
                }

                // Validate Voucher Code
                const voucherCode = $('#voucherCode').val();
                if (!voucherCode) {
                    $('#codeError').text('Mã voucher không được để trống.');
                    isValid = false;
                } else if (!/^[A-Za-z0-9]{5}$/.test(voucherCode)) {
                    $('#codeError').text('Mã voucher chỉ chấp nhận 5 kí tự chữ và số.');
                    isValid = false;
                }

                // Validate Date Range
                const startDate = new Date($('#startDate').val());
                const endDate = new Date($('#endDate').val());
                if (!startDate || !endDate) {
                    $('#dateError').text('Cả ngày bắt đầu và ngày kết thúc đều phải có giá trị.');
                    isValid = false;
                } else if (endDate <= startDate) {
                    $('#dateError').text('Ngày kết thúc phải lớn hơn ngày bắt đầu.');
                    isValid = false;
                }

                // Validate Discount Percentage
                const discountPercentage = $('#discountPercentage').val();
                if (!discountPercentage) {
                    $('#precentError').text('Phần trăm giảm giá không được để trống.');
                    isValid = false;
                } else if (discountPercentage < 0 || discountPercentage > 100) {
                    $('#precentError').text('Phần trăm giảm giá phải trong khoảng 0 đến 100.');
                    isValid = false;
                }

                // Validate Minimum Order Value
                const minOrderValue = $('#minOrderValue').val();
                if (!minOrderValue) {
                    $('#minOrderValueError').text('Giá trị đơn hàng tối thiểu không được để trống.');
                    isValid = false;
                }

                // Validate Maximum Amount
                const usageLimit = $('#usageLimit').val();
                if (!usageLimit) {
                    $('#maximumAmountError').text('Số tiền giảm tối đa không được để trống.');
                    isValid = false;
                }

                return isValid;
            }

            // Function to send form data via AJAX
            function updateOrAddBillDiscount() {
                const idBillDiscount = $('#id-billdiscount').val() ;
                const discountProgram = $('#discountProgram').val();
                const voucherCode = $('#voucherCode').val();
                const startDate = $('#startDate').val();
                const endDate = $('#endDate').val();
                const discountPercentage = $('#discountPercentage').val();
                const minOrderValue = $('#minOrderValue').val();
                const usageLimit = $('#usageLimit').val();
                const isOutstanding = $('#isOutstanding').is(':checked');

                // Determine the custom score based on category select value
                let loyaltyPointsRequired = null;
                const categorySelectValue = $('#categorySelect').val();
                if (categorySelectValue === "custom") {
                    // If 'custom' is selected, take value from the customScore input field
                    loyaltyPointsRequired = $('#customScore').val();
                    if (!loyaltyPointsRequired) {
                        $('#scoreError').text('Vui lòng nhập số điểm tùy chỉnh.');
                        return; // Stop the submission if custom score is empty
                    }
                } else {
                    // If not 'custom', get the value from the selected option's data-id
                    loyaltyPointsRequired = categorySelectValue;
                }

                // Prepare data to send
                const data = {
                    id : idBillDiscount,
                    minimumInvoiceAmount: minOrderValue,
                    name: discountProgram,
                    startDate: startDate,
                    endDate: endDate,
                    discountPercentage: discountPercentage,
                    maximumAmount: usageLimit,
                    code: voucherCode,
                    isOutStanding: isOutstanding,
                    loyaltyPointsRequired: loyaltyPointsRequired // Send the selected or custom score
                };

                sendData(data,'/api-bill-discount','POST')
            }

            // Function to send form data via AJAX
            function cancelBillDiscount() {
                const idBillDiscount = $('#id-billdiscount').val() ;

                // Prepare data to send
                const data = {
                    id : idBillDiscount,
                };

                sendData(data,'/api-bill-discount','DELETE')
            }

            function sendData (data,url,method) {
                $.ajax({
                    url: url,
                    method: method,
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    beforeSend: function () {
                        // Hiển thị loader trước khi AJAX bắt đầu
                        $('#global-loader').css('display', 'flex');
                    },
                    success: function(response) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Thành công',
                            text: response.toString()
                        }).then(() => {
                            window.location.href = 'giam-gia-cho-don-hang'
                        });
                    },
                    error: function(xhr, status, error) {
                        let notice;

                        if (xhr.status === 409) {
                            notice = ("Lỗi: " + xhr.responseText); // Hiển thị thông báo lỗi khi mã code đã tồn tại
                        } else if (xhr.status === 400) {
                            notice = ("Lỗi: Dữ liệu không hợp lệ.");
                        } else {
                            notice = ("Đã xảy ra lỗi: " + error);
                        }

                        Swal.fire({
                            icon: 'error',
                            title: 'Lỗi',
                            text: notice
                        });
                    },
                    complete: function () {
                        $('#global-loader').css('display', 'none');
                    }
                });
            }

            // Handle form submission
            $('#add-Product').click(function(e) {
                e.preventDefault(); // Prevent default form submission
                if (checkInput()) {
                    showConfirmationModal().then((result) => {
                        if (!result) {
                            console.log("User cancelled the action.");
                            return; // Người dùng chọn "Cancel", dừng xử lý
                        }
                        updateOrAddBillDiscount(); // Send data if inputs are valid
                    });
                }
            });

            $('#cancel-btn').click(function (e) {
                e.preventDefault();
                showConfirmationModal().then((result) => {
                    if (!result) {
                        console.log("User cancelled the action.");
                        return; // Người dùng chọn "Cancel", dừng xử lý
                    }
                    cancelBillDiscount();
                });
            });
        });

    </script>

</div>



