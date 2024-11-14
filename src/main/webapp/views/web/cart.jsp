<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


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
                        <a href="<c:url value="/trang-chu"/>">Home</a>
                        <a href="<c:url value="/san-pham"/>">Shop</a>
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
                            <th>Sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Tổng tiền</th>
                            <th></th>
                            <th>Chọn</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="entry" items="${sessionScope.cart}">
                            <c:set var="itemId" value="${entry.key}" />
                            <c:set var="item" value="${entry.value}" />
                            <tr>
                                <td class="product__cart__item">
                                    <div class="product__cart__item__pic">
                                        <img style="width: 100px" src="<c:url value='/static/img/product/aothun1-gray.png'/>" alt="${item.productVariant.name}">
<%--                                        <img src="<c:url value='/api-image?path=${item.productVariant.imageUrl}'/>" alt="${item.productVariant.name}">--%>
                                    </div>
                                    <div class="product__cart__item__text">
                                        <h6>${item.productVariant.name}</h6>
                                        <h6>Size: ${item.productVariant.size}</h6>
                                        <h6>Color: ${item.productVariant.color}</h6>
                                        <h6>Price: ${item.productVariant.price}</h6>
                                    </div>
                                </td>
                                <td class="quantity__item">
                                    <div class="quantity">
                                        <div class="pro-qty-2">
                                            <input type="text" value="${item.quantity}" data-product-id="${item.productVariant.id}">
                                        </div>
                                    </div>
                                </td>
                                <td class="cart__price">$ ${item.productVariant.price * item.quantity}</td>

                                <td></td>
                                <td class="cart__close">
                                    <div style="display: flex; align-items: center; justify-content: center; gap: 10px">
                                        <input type="checkbox" />
                                        <a href="javascript:void(0);" onclick="removeFromCart(${item.productVariant.id})">
                                            <i class="fa fa-close"></i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="continue__btn">
                            <a href="<c:url value='/danh-sach-san-pham' />">Continue Shopping</a>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="continue__btn update__btn">
                            <c:if test="${empty cookie.token}">
                                <a href="<c:url value="/dang-nhap"/>"><i class="fa fa-spinner"></i> Update cart</a>
                            </c:if>
                            <c:if test="${not empty cookie.token}">
                                <a href="javascript:void(0);" onclick="updateCart()"><i class="fa fa-spinner"></i> Update cart</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="cart__discount">
                    <h6 style="cursor: pointer;" data-bs-toggle="modal" data-bs-target="#form2Modal">Discount codes</h6>
                    <form action="#" onsubmit="applyCoupon(event)">
                        <input type="text" id="couponCode" placeholder="Coupon code">
                        <button type="submit">Apply</button>
                    </form>
                </div>


                <div id="discountContent" style="display: none;">
                    <div class="row text-info d-flex align-items-center">
                        <div class="col-1">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="col-2 text-info">
                            <input type="text" id="title" style="border: none; color:#17a2b8;" data-bs-toggle="modal"
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
                    <div class="row text-info d-flex align-items-center mb-5">
                        <div class="col-1">
                            <i class="fas fa-ticket"></i>
                        </div>
                        <div class="col-2 text-info">
                            <input type="text" id="title1" style="border: none; color:#17a2b8; cursor: pointer;"
                                   data-bs-toggle="modal" data-bs-target="#form2Modal" readonly>
                        </div>
                    </div>
                </div>

                <%-- Áp dụng mã giảm giá --%>
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
                                <c:forEach items="${discountList}" var="o">
                                    <div class="card mb-3">
                                        <div class="card-body">
                                            <div class="row ps-1">
                                                <h5>${o.description}</h5>
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
                                            <div id="extraContent${o.code}" class="collapse">
                                                <h6>• Áp dụng với đơn hàng trên ${o.minimumPurchaseQuantity} sản phẩm.</h6>
                                            </div>

                                            <div class="row">
                                                <div class="col-8">
                                                    <!-- Sử dụng data-bs-target với id riêng biệt -->
                                                    <button type="button" class="btn btn-link p-0 text-decoration-none"
                                                            data-bs-toggle="collapse"
                                                            data-bs-target="#extraContent${o.code}" aria-expanded="false"
                                                            aria-controls="extraContent${o.code}"
                                                    >Xem chi tiết ⬎</button>
                                                </div>
                                                <div class="col-4">
                                                    <button type="button" class="btn btn-dark w-100"
                                                            data-bs-dismiss="modal"
                                                            data-code="${o.code}" data-description="${o.description}"
                                                            data-percentCoupon="${o.discountPercentage}">Áp dụng</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="modal-footer d-flex justify-content-center">
                                <button type="button" class="btn btn-link p-0 text-decoration-none"
                                        data-bs-toggle="collapse" data-bs-target="#extraContent2" aria-expanded="false"
                                        aria-controls="extraContent" ></button>
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
                    <a href="<c:url value='/' />" class="primary-btn"  id="PlacedOrder">Proceed to checkout</a>
                </div>
            </div>
        </div>
    </div>
</section>

<script>

    $(document).ready(function () {

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
        });


        // Sự kiện chọn checkbox
        $('input[type="checkbox"]').on('change', function () {
            calculateTotalPrice();
        });

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
                alert("Cập nhật giỏ hàng thành công.");
                refreshCart();
            },
            error: function(xhr) {
                alert("Không thể cập nhật giỏ hàng.");
            }
        });
    }

    function removeFromCart(productVariantId) {
        console.log("Deleting productVariantId: ", productVariantId);
        $.ajax({
            type: "POST",
            url: "/xoa-gio-hang",
            data: { productVariantId: productVariantId },
            success: function(response) {
                alert("Xóa sản phẩm khỏi giỏ hàng thành công.");
                refreshCart();
            },
            error: function(xhr) {
                alert("Không thể xóa sản phẩm khỏi giỏ hàng.");
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
                $('#total-price').text('$ ' + response.totalPrice);
                location.reload();
            },
            error: function(xhr) {
                alert("Không thể tải giỏ hàng.");
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
    function applyCoupon(button) {
        var couponCode = button.getAttribute("data-code");
        var descriptionCoupon = button.getAttribute("data-description");
        var percentCoupon = button.getAttribute("data-percentCoupon");
        document.getElementById("title").value = "Áp dụng thành công!";
        document.getElementById("title1").value = "Xem thêm";
        document.getElementById("couponCode").value = couponCode;
        document.getElementById("descriptionCoupon").value = descriptionCoupon + ":";
        document.getElementById("percentCoupon").value = "-" + percentCoupon + "%";
        document.getElementById("discountContent").style.display = "block";
    }

</script>
<!-- Shopping Cart Section End -->
