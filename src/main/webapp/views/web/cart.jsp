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
                                        <img style="width: 100px" src="<c:url value='/api-image?path=${item.productVariant.imageUrl}'/>" alt="${item.productVariant.name}">
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
                                            <input type="checkbox" ${item.isActive == 1 ? 'checked' : ''}/>
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
                    <h6>Discount codes</h6>
                    <form action="#">
                        <input id="discount-code" type="text" placeholder="Coupon code">
                        <button type="submit">Apply</button>
                    </form>
                </div>
                <div class="cart__total">
                    <h6>Cart total</h6>
                    <ul>
                        <li>Subtotal <span>$ 0</span></li>
                        <li>Total <span id="total-price">$ ${sessionScope.totalPrice}</span></li>
                    </ul>
                    <a href="javascript:void(0);" class="primary-btn"  id="ProceedToCheckout">Proceed to checkout</a>
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


    $('#ProceedToCheckout').click(function (event) {


        event.preventDefault();
        const selectedProducts = getSelectedProducts();
        const billDiscountCode = $('#discount-code').val();


        if (selectedProducts.length === 0) {
            alert("Vui lòng chọn ít nhất một sản phẩm để thanh toán.");
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
                    alert(response.message.toString());
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
                alert("Có lỗi xảy ra, vui lòng thử lại.");
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
<!-- Shopping Cart Section End -->

