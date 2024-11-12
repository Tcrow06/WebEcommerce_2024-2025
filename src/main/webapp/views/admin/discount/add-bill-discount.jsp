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
        </div>
    </div>


    <div class="container mt-5">
        <h2 class="mb-4">Tạo mã giảm giá mới</h2>

        <!-- Basic Info Section -->
        <form>
            <div class="card p-4">
                <h5 class="card-title">Thông tin cơ bản</h5>

                <!-- Voucher Type -->
                <div class="btn-group btn-group-toggle mb-4" data-toggle="buttons">
                    <label class="btn btn-outline-secondary active">
                        Voucher toàn Shop
                    </label>
                </div>

                <!-- Discount Program Name -->
                <div class="form-group">
                    <label for="discountProgram">Tên chương trình giảm giá</label>
                    <input type="text" class="form-control" id="discountProgram" placeholder="km1">
                    <small class="form-text text-muted">Tên Voucher sẽ không được hiển thị cho Người mua</small>
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
                            <input type="text" class="form-control" id="minOrderValue" placeholder="Nhập vào">
                            <div class="error-message" id="minOrderValueError" style="font-size: 12px"></div>
                        </div>

                        <!-- Usage Limit -->
                        <div class="form-group">
                            <label for="usageLimit">Số tiền được giảm tối đa</label>
                            <input type="text" class="form-control" id="usageLimit" placeholder="Nhập vào">
                            <small class="form-text text-muted">Số tiền được giảm tối đa</small>
                            <div class="error-message" id="maximumAmountError" style="font-size: 12px"></div>
                        </div>

                        <!-- Usage Limit -->
                        <div class="form-group">
                            <label for="usageLimit">Lượt sử dụng tối đa</label>
                            <input type="text" class="form-control" id="minimumPurchaseQuantity" placeholder="Nhập vào">
                            <small class="form-text text-muted" id="maximumAmountQuantity" >Tổng số Mã giảm giá có thể sử dụng</small>
                            <div class="error-message" id="minimumPurchaseQuantityError" style="font-size: 12px"></div>
                        </div>

                    </div>

                </form>
            </div>

            <!-- Buttons -->
            <div class="mt-4">
                <button type="button" class="btn btn-secondary">Hủy</button>
                <button type="submit" class="btn btn-primary">Xác nhận</button>
            </div>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
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

                // Validate Minimum Purchase Quantity
                const minimumPurchaseQuantity = $('#minimumPurchaseQuantity').val();
                if (!minimumPurchaseQuantity) {
                    $('#minimumPurchaseQuantityError').text('Lượt sử dụng tối đa không được để trống.');
                    isValid = false;
                }

                return isValid;
            }

            // Function to send form data via AJAX
            function sendData() {
                const discountProgram = $('#discountProgram').val();
                const voucherCode = $('#voucherCode').val();
                const startDate = $('#startDate').val();
                const endDate = $('#endDate').val();
                const discountPercentage = $('#discountPercentage').val();
                const minOrderValue = $('#minOrderValue').val();
                const usageLimit = $('#usageLimit').val();
                const minimumPurchaseQuantity = $('#minimumPurchaseQuantity').val();
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
                    minimumInvoiceAmount: minOrderValue,
                    name: discountProgram,
                    startDate: startDate,
                    endDate: endDate,
                    discountPercentage: discountPercentage,
                    maximumAmount: usageLimit,
                    minimumPurchaseQuantity: minimumPurchaseQuantity,
                    code: voucherCode,
                    isOutStanding: isOutstanding,
                    loyaltyPointsRequired: loyaltyPointsRequired // Send the selected or custom score
                };

                // Send the data using AJAX
                $.ajax({
                    url: '/api-bill-discount',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function(response) {
                        alert('Mã giảm giá đã được tạo thành công!');
                    },
                    error: function(xhr, status, error) {
                        alert('Có lỗi xảy ra khi gửi dữ liệu!');
                    }
                });
            }

            // Handle form submission
            $('button[type="submit"]').click(function(e) {
                e.preventDefault(); // Prevent default form submission
                if (checkInput()) {
                    sendData(); // Send data if inputs are valid
                }
            });
        });

    </script>

</div>



