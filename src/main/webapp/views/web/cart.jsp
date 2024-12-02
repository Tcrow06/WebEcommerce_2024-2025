<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
    input[type="checkbox"] {
        width: 20px;
        height: 20px;
        border-radius: 50%; /* Bo tròn */
        border: 2px solid #ccc; /* Đường viền nhạt */
        appearance: none; /* Ẩn giao diện mặc định */
        cursor: pointer;
        transition: background-color 0.3s ease;
    }


    input[type="checkbox"]:checked {
        background-color: black; /* Màu đen khi được chọn */
        border-color: black;
    }
</style>


<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__text">
                    <h4>Shopping Cart</h4>
                    <div class="breadcrumb__links">
                        <a href="<c:url value='/trang-chu' />">Home</a>

                        <a href="<c:url value='/san-pham' />">Shop</a>

                        <span>Shopping Cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->


<!-- Shopping Cart Section Begin -->
<section class="shopping-cart spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div id="cart-container" class="shopping__cart__table">
                    <table>
                        <thead>
                        <tr>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="entry" items="${sessionScope.cart}">
                            <c:set var="itemId" value="${entry.key}" />
                            <c:set var="item" value="${entry.value}" />
                            <tr>
                                <td class="product__cart__item"
                                <c:if test="${item.productVariant.status == 'STOP_SELLING'}">
                                    style="opacity: 0.6"
                                </c:if>
                                >
                                    <div class="product__cart__item__pic">
                                        <c:if test="${item.productVariant.status == 'STOP_SELLING'}">
                                            <img style="width: 100px; opacity: 0.4;" src="<c:url value='/api-image?path=${item.productVariant.imageUrl}'/>" alt="${item.productVariant.name}">
                                        </c:if>
                                        <c:if test="${item.productVariant.status != 'STOP_SELLING'}">
                                            <img style="width: 100px"; src="<c:url value='/api-image?path=${item.productVariant.imageUrl}'/>" alt="${item.productVariant.name}">
                                        </c:if>

                                    </div>
                                    <div class="product__cart__item__text">
                                        <c:if test="${item.productVariant.status == 'STOP_SELLING'}">
                                            <h6>${item.productVariant.name} - <span style="color: red">Ngưng kinh doanh</span></h6>
                                        </c:if>
                                        <c:if test="${item.productVariant.status != 'STOP_SELLING'}">
                                            <h6>${item.productVariant.name}</h6>
                                        </c:if>
                                        <h6>Size: ${item.productVariant.size}</h6>
                                        <h6>Color: ${item.productVariant.color}</h6>
                                        <h6>Price: ${item.productVariant.price}</h6>
                                    </div>
                                </td>
                                <td class="quantity__item">
                                    <div class="quantity">
                                        <c:if test="${item.productVariant.status == 'STOP_SELLING'}">

                                        </c:if>
                                        <c:if test="${item.productVariant.status != 'STOP_SELLING'}">
                                            <div class="pro-qty-2">
                                                <input type="text" value="${item.quantity}" data-product-id="${item.productVariant.id}">
                                            </div>
                                        </c:if>

                                    </div>
                                </td>
                                <td class="cart__price">$ ${item.productVariant.price * item.quantity}</td>
                                <td class="cart__close">
                                    <c:if test="${item.productVariant.status == 'STOP_SELLING'}">

                                    </c:if>
                                    <c:if test="${item.productVariant.status != 'STOP_SELLING'}">
                                        <div style="display: flex; align-items: center; justify-content: center; gap: 10px">
                                            <input type="checkbox" data-product-id="${item.productVariant.id}" ${item.isActive == 1 ? 'checked' : ''}/>
                                        </div>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Nút Xóa cho các sản phẩm được chọn -->
                <div id="delete-selected-container" style="display: none; margin-bottom: 15px;">
                    <button class="btn btn-danger" onclick="removeSelectedFromCart()">Xóa sản phẩm đã chọn</button>
                </div>

                <div class="row">
                    <div class="row align-items-center justify-content-between">

                        <div class="col-lg-6 col-md-4 col-sm-4">
                            <div class="continue__btn">
                                <a href="<c:url value='/danh-sach-san-pham' />">Tiếp tục mua sắm</a>
                            </div>
                        </div>

                        <div class="col-lg-6 col-md-4 col-sm-4">
                            <div class="continue__btn update__btn">
                                <c:if test="${empty cookie.token}">
                                    <a href="<c:url value='/dang-nhap' />"><i class="fa fa-spinner"></i>Cập nhật giỏ hàng</a>
                                </c:if>
                                <c:if test="${not empty cookie.token}">
                                    <a href="javascript:void(0);" onclick="updateCart()"><i class="fa fa-spinner"></i>Cập nhật giỏ hàng</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="cart__discount">
                    <h6 style="cursor: pointer;" data-bs-toggle="modal" data-bs-target="#form2Modal">Discount codes</h6>
                    <form onsubmit="return false;">
                        <input type="text" id="couponCode" placeholder="Coupon code" required>
                        <button type="button" class="btn btn-primary"
                                onclick="sendCouponCode()">
                            Áp Dụng
                        </button>
                    </form>
                </div>


                <div id="couponContent"></div>


                <div id="discountContent" style="display: none;">
                    <div class="row text-info d-flex align-items-center">
                        <div class="col-1">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="col-2 text-info">
                            <input type="text" id="title" style="border: none; color:#17a2b8 ;" data-bs-toggle="modal"
                                   data-bs-target="#form2Modal" readonly>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-1 text-info">
                            <i class="fa-solid fa-arrow-right"></i>
                        </div>
                        <div class="col-5">
                            <input type="text" id="descriptionCoupon"
                                   style="border: none; background: transparent; pointer-events: none; font-weight: bold"
                                   readonly>
                        </div>
                        <div class="col-1 text-info fw-bold">
                            <input type="text" id="percentCoupon"
                                   style="border: none; background: transparent; pointer-events: none; font-weight: bold; color: #17a2b8;"
                                   readonly>
                        </div>
                    </div>
                    <div class="row text-info d-flex align-items-center fw-bold" style="border: none; background: transparent; pointer-events: none; font-weight: bold">
                        <div class = "col-8">
                            <h6>• Áp dụng với đơn hàng trên: </h6>
                        </div>
                        <div class = "col-2 mb-1 ">
                            <input type="text" id="minimumInvoiceAmount" style="border: none; color:#17a2b8 ;" readonly>
                        </div>
                        <div class = "col-2"></div>
                    </div>
                    <div class="row text-black d-flex align-items-center fw-bold" style="border: none; background: transparent; pointer-events: none; font-weight: bold">
                        <div class="col-8">
                            <h6>• Số tiền giảm tối đa: </h6>
                        </div>
                        <div class = "col-2 mb-1">
                            <input type="text" id="maximumAmount" style="border: none; color:#17a2b8 ;" readonly>
                        </div>
                        <div class = "col-2"></div>
                    </div>
                    <div class="row text-info d-flex align-items-center mb-5">
                        <div class="col-1">
                            <i class="fas fa-ticket"></i>
                        </div>
                        <div class="col-2 text-info">
                            <input type="text" id="title1" style="border: none; color:#17a2b8 ; cursor: pointer;"
                                   data-bs-toggle="modal" data-bs-target="#form2Modal" readonly>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="form2Modal" tabindex="-1" aria-labelledby="form2ModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content shadow">
                            <div class="modal-header">
                                <h4 class="modal-title" id="form2ModalLabel">Chọn mã giảm giá</h4>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body" style="max-height: 400px; overflow-y: auto;">
                                <c:if test="${not empty discountList}">
                                    <c:forEach items="${discountList}" var="o">
                                        <div class="card mb-3">
                                            <div class="card-body">
                                                <div class="row ps-1">
                                                    <h5 class="fw-bold">${searchDiscount.name}</h5>
                                                </div>
                                                <div class="row">
                                                    <div class="col-2">
                                                        <div class="fs-3 text-dark pb-2">
                                                            <i class="fas fa-ticket"></i>
                                                        </div>
                                                    </div>
                                                    <div class="col-10">
                                                        <strong>${o.code}</strong><br>
                                                        <p class="text-secondary-emphasis">Hạn sử dụng: ${o.endDate}</p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <h6>• Giảm ${o.discountPercentage}%</h6>
                                                </div>
                                                <div id="extraContent${o.code}" class="collapse"
                                                     data-minimum-invoice-amount="${o.minimumInvoiceAmount}"
                                                     data-maximum-amount="${o.maximumAmount}">
                                                    <h6>• Áp dụng với đơn hàng trên: ${o.minimumInvoiceAmount} VND.</h6>
                                                    <h6>• Số tiền giảm tối đa: ${o.maximumAmount} VND.</h6>
                                                </div>


                                                <div class="row">
                                                    <div class="col-8">
                                                        <!-- Sử dụng data-bs-target với id riêng biệt -->
                                                        <button type="button" class="btn btn-link p-0 text-decoration-none"
                                                                data-bs-toggle="collapse"
                                                                data-bs-target="#extraContent${o.code}" aria-expanded="false"
                                                                aria-controls="extraContent${o.code}"
                                                                onclick="toggleButtonText(this)">Xem chi tiết ⬎</button>
                                                    </div>
                                                    <div class="col-4">
                                                        <button type="button" class="btn btn-dark w-100"
                                                                data-bs-dismiss="modal" onclick="applyCoupon(this)"
                                                                data-code="${o.code}" data-description="${o.name}"
                                                                data-minInvoiceAmount = "${o.minimumInvoiceAmount}"
                                                                data-maxAmount = "${o.maximumAmount}"
                                                                data-percentCoupon="${o.discountPercentage}">Áp dụng</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cart__total">
                    <h6>Cart total</h6>
                    <ul>
                        <li>Subtotal <span>$ 0</span></li>
                        <li>Total <span id="total-price">$ ${sessionScope.totalPrice}</span></li>
                    </ul>
                    <a href="javascript:void(0);" class="primary-btn"  id="ProceedToCheckout">Đặt hàng</a>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    $(document).ready(function () {
        calculateTotalPrice();
        toggleDeleteButton();

        // Sự kiện click vào nút tăng/giảm số lượng
        $(document).on('click', '.pro-qty-2 .qtybtn', function () {
            let $button = $(this);
            let $input = $button.siblings('input');
            let oldValue = parseInt($input.val(), 10);

            if (isNaN(oldValue)) {
                oldValue = 1;
            }

            if (!$button.hasClass('inc')) {
                oldValue = (oldValue > 1) ? oldValue : 1;
            }
            $input.val(oldValue);

            updateTotalPrice($input);
            afterUseVoucher();
        });

        // Sự kiện chọn checkbox
        $('input[type="checkbox"]').on('change', function () {
            calculateTotalPrice();
            toggleDeleteButton();
        });
        function afterUseVoucher() {
            var totalPrice = parseFloat(document.getElementById("total-price").innerText.replace("$", "").trim());
            var minimumInvoiceAmount = parseFloat(document.getElementById("minimumInvoiceAmount").value);

            if (!isNaN(minimumInvoiceAmount)) {
                if (totalPrice < minimumInvoiceAmount) {
                    document.getElementById("discountContent").style.display = "none";
                    document.getElementById("percentCoupon").value = "0";
                    document.querySelector(".cart__total ul li span").innerText = "$ 0.00";
                    document.getElementById("total-price").innerText = "$ " + totalPrice.toFixed(2);
                }
                else {
                    var percentCoupon = parseFloat(document.getElementById("percentCoupon").value);
                    var maximumAmount = parseFloat(document.getElementById("maximumAmount").value);


                    var newSubtotal = (totalPrice * percentCoupon) / 100;

                    if (newSubtotal > maximumAmount) {
                        newSubtotal = maximumAmount;
                    }

                    var newTotalPrice = totalPrice - newSubtotal;

                    document.querySelector(".cart__total ul li span").innerText = "$ " + newSubtotal.toFixed(2);
                    document.getElementById("total-price").innerText = "$ " + newTotalPrice.toFixed(2);
                }
            }
        }

        // Hiển thị/ẩn nút "Xóa"
        function toggleDeleteButton() {
            let isAnyChecked = $('input[type="checkbox"]:checked').length > 0;
            $('#delete-selected-container').toggle(isAnyChecked);
        }
        // Hàm cập nhật giá cho từng sản phẩm
        function updateTotalPrice(inputElement) {
            const productVariantId = $(inputElement).data('product-id');
            const quantity = parseInt($(inputElement).val(), 10);
            const pricePerUnit = parseFloat(
                $(inputElement).closest('tr').find('.product__cart__item__text h6:last').text().replace(/[^\d.]/g, '')
            );

            const totalPrice = (quantity * pricePerUnit).toFixed(2);
            $(inputElement).closest('tr').find('.cart__price').text('$ ' + totalPrice);

            // Cập nhật tổng giá
            calculateTotalPrice();
        }

        // Hàm tính tổng giá khi chọn các sản phẩm
        function calculateTotalPrice() {
            let total = 0;
            $('input[type="checkbox"]:checked').each(function () {
                const priceText = $(this).closest('tr').find('.cart__price').text();
                const price = parseFloat(priceText.replace(/[^\d.]/g, ''));
                total += price;
            });
            $('#total-price').text('$ ' + total.toFixed(2));
        }
    });


    function updateCart() {
        const cartData = getCartData();
        $.ajax({
            type: "POST",
            url: "/sua-gio-hang",
            contentType: "application/json",
            data: JSON.stringify({
                cartItems: cartData
            }),
            success: function(response) {
                Swal.fire({
                    icon: 'success',
                    title: 'Thông báo',
                    text: 'Cập nhật giỏ hàng thành công.',
                }).then(() => {
                    refreshCart();
                });
            },
            error: function(xhr) {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi hệ thống',
                    text: 'Cập nhật giỏ hàng thất bại, liên hệ với quản trị viên để được giải quyết.',
                });
            }
        });
    }

    // Hàm xóa các sản phẩm đã chọn
    function removeSelectedFromCart() {
        const selectedItems = [];

        // Lặp qua tất cả các checkbox đã chọn
        $('input[type="checkbox"]:checked').each(function () {
            const productVariantId = $(this).data('product-id');
            const quantity = $(this).closest('tr').find('input[type="text"]').val();

            selectedItems.push(productVariantId);
        });

        $.ajax({
            type: "POST",
            url: "/xoa-gio-hang",
            contentType: "application/json",
            data: JSON.stringify(selectedItems),
            success: function(response) {
                Swal.fire({
                    icon: 'success',
                    title: 'Thông báo',
                    text: 'Xóa sản phẩm khỏi giỏ hàng thành công.',
                }).then(() => {
                    refreshCart();
                });
            },
            error: function(xhr) {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi hệ thống',
                    text: 'Xóa sản phẩm hỏi giỏ hàng thất bại, liên hệ với quản trị viên để được giải quyết.',
                });
            }
        });
    }


    function getCartData() {
        const cartData = [];
        $('#cart-container tbody tr').each(function() {
            const productVariantId = $(this).find('input').data('product-id');
            const quantity = $(this).find('input').val();

            if (productVariantId && quantity) {
                cartData.push({
                    productVariantId: productVariantId,
                    quantity: parseInt(quantity, 10)
                });
            }
        });
        return cartData;
    }

    function refreshCart() {
        $.ajax({
            type: "GET",
            url: "/gio-hang",
            success: function(response) {
                $('#cart-container').html($(response).find('#cart-container').html());
                location.reload();
            },
            error: function(xhr) {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi hệ thống',
                    text: 'Giỏ hàng không thể tải. Đừng lo! Mọi chuyện sẽ được giải quyết nhanh thôi.',
                })
            }
        });
    }

    // Phần quản lý mã giảm giá
    function toggleButtonText(button) {
        if (button.textContent === "Xem chi tiết ⬎") {
            button.textContent = "Thu gọn ⬏";
        } else {
            button.textContent = "Xem chi tiết ⬎";
        }
    }
    function sendCouponCode() {
        var couponCode = document.getElementById("couponCode").value;

        if (!couponCode) {
            Swal.fire({
                icon: 'warning',
                title: 'Cảnh báo',
                text: 'Mã giảm giá không được để trống.',
            });
            // alert("Mã giảm giá không được để trống.");
            return;
        }

        fetch('/tim-kiem-ma-giam-gia', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ couponCode }),
        })
            .then(response => {
                if (response.ok) return response.json();
                throw new Error("Mã giảm giá không hợp lệ hoặc đã hết hạn.");
            })
            .then(data => {
                console.log("Data received:", data); // Kiểm tra cấu trúc của dữ liệu

                if (!data || !data.code || !data.name) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Cảnh báo',
                        text: 'Dữ liệu không đầy đủ.',
                    });
                    // alert("Dữ liệu không đầy đủ.");
                    return;
                }

                let code = data.code;
                let name = data.name;
                let percent = data.discountPercentage;
                let minimumInvoiceAmount = data.minimumInvoiceAmount;
                let maximumAmount = data.maximumAmount;

                console.log("Data to display:", data);
                Swal.fire({
                    icon: 'success',
                    title: 'Thông báo',
                    text: 'Mã giảm giá áp dụng thành công!',
                });
                // alert("Mã giảm giá áp dụng thành công!");
                applyCouponAfterSearch(code, name, percent, minimumInvoiceAmount, maximumAmount);
            })
            .catch(error => {
                console.error(error);
                alert(error.message);
            });
    }



    function applyCoupon(button) {
        var totalPrice = parseFloat(document.getElementById("total-price").innerText.replace("$", "").trim());
        var minimumInvoiceAmount = button.getAttribute("data-minInvoiceAmount");
        if(totalPrice > minimumInvoiceAmount)
        {
            var couponCode = button.getAttribute("data-code");
            var descriptionCoupon = button.getAttribute("data-description");
            var percentCoupon = button.getAttribute("data-percentCoupon");
            var maximumAmount = button.getAttribute("data-maxAmount");

            document.getElementById("title").value = "Áp dụng thành công!";
            document.getElementById("title1").value = "Xem thêm";
            document.getElementById("couponCode").value = couponCode;
            document.getElementById("descriptionCoupon").value = descriptionCoupon + ":";
            document.getElementById("percentCoupon").value = percentCoupon + "%";
            document.getElementById("maximumAmount").value = maximumAmount + " VND.";
            document.getElementById("minimumInvoiceAmount").value =minimumInvoiceAmount + " VND.";
            document.getElementById("discountContent").style.display = "block";

            var percentCoupon1 = parseFloat(document.getElementById("percentCoupon").value);
            var maximumAmount1 = parseFloat(document.getElementById("maximumAmount").value);


            var newSubtotal = (totalPrice * percentCoupon1) / 100;

            if (newSubtotal > maximumAmount1) {
                newSubtotal = maximumAmount1;
            }

            var newTotalPrice = totalPrice - newSubtotal;

            document.querySelector(".cart__total ul li span").innerText = "$ " + newSubtotal.toFixed(2);
            document.getElementById("total-price").innerText = "$ " + newTotalPrice.toFixed(2);
        }
        else
        {
            Swal.fire({
                icon: 'warning',
                title: 'Cảnh báo',
                text: 'Bạn không đủ điều kiện sử dụng mã giảm giá. Hãy kiểm tra lại.',
            });
            // alert("Bạn không đủ điều kiện sử dụng mã giảm giá. Hãy kiểm tra lại.");
        }
    }
    function applyCouponAfterSearch(code, name, percent, minimumInvoiceAmount, maximumAmount) {
        var totalPrice = parseFloat(document.getElementById("total-price").innerText.replace("$", "").trim());

        if (totalPrice >= minimumInvoiceAmount) {
            document.getElementById("title").value = "Áp dụng thành công!";
            document.getElementById("title1").value = "Xem thêm";
            document.getElementById("couponCode").value = code;
            document.getElementById("descriptionCoupon").value = name + ":";
            document.getElementById("percentCoupon").value = percent + "%";
            document.getElementById("maximumAmount").value = maximumAmount + " VND";
            document.getElementById("minimumInvoiceAmount").value = minimumInvoiceAmount + " VND";
            document.getElementById("discountContent").style.display = "block";

            var discountAmount = (totalPrice * percent) / 100;
            if (discountAmount > maximumAmount) {
                discountAmount = maximumAmount;
            }

            var newTotalPrice = totalPrice - discountAmount;

            document.querySelector(".cart__total ul li span").innerText = "$ " + discountAmount.toFixed(2);
            document.getElementById("total-price").innerText = "$ " + newTotalPrice.toFixed(2);
        } else {
            Swal.fire({
                icon: 'warning',
                title: 'Cảnh báo',
                text: 'Bạn không đủ điều kiện sử dụng mã giảm giá. Hãy kiểm tra lại.',
            });
            // alert("Bạn không đủ điều kiện sử dụng mã giảm giá. Hãy kiểm tra lại.");
        }
    }

    $('#ProceedToCheckout').click(function (event) {

        event.preventDefault();
        const selectedProducts = getSelectedProducts();
        const billDiscountCode = $('#couponCode').val();


        if (selectedProducts.length === 0) {
            Swal.fire({
                icon: 'warning',
                title: 'Cảnh báo',
                text: 'Vui lòng chọn sản phẩm để thanh toán.',
            });
            return;
        }
        $.ajax({
            type: "POST",
            url: "/kiem-tra-san-pham",
            contentType: "application/json",
            data: JSON.stringify({
                selectedProductsId: selectedProducts,
                billDiscountCode: billDiscountCode
            }),
            success: function(response) {
                if(response.status==="warring"){
                    window.location.href = response.redirectUrl.toString() ;
                }
                else if(response.status==="error"){
                    Swal.fire({
                        icon: 'error',
                        title: 'Thất bại',
                        text: response.message,
                    });
                }
                // Xử lý khi thành công
                else{
                    window.location.href = response.redirectUrl.toString() + "?order=" + encodeURIComponent(JSON.stringify(response.order));
                }
            },
            error: function(xhr, status, error) {
                window.location.href = response.redirectUrl.toString() + "?order=" + encodeURIComponent(JSON.stringify(response.order));
                // Xử lý khi có lỗi
                console.error("Lỗi: ", error);
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi hệ thống',
                    text: 'Có vẻ hệ thống đã gặp vấn đề. Đừng lo ! Mọi chuyện sẽ được giải quyết.',
                });
            }
        });
    });




    function getSelectedProducts() {
        const selectedProducts = [];
        $('#cart-container tbody tr').each(function() {
            const checkbox = $(this).find('input[type="checkbox"]');
            if (checkbox.is(':checked')) {
                const productVariantId = $(this).find('input[type="text"]').data('product-id');
                const quantity = $(this).find('input[type="text"]').val();
                if (productVariantId && quantity) {
                    selectedProducts.push({
                        productVariantId: productVariantId,
                        quantity: parseInt(quantity, 10)
                    });
                }
            }
        });
        return selectedProducts;
    }
</script>