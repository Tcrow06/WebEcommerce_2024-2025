<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                <div class="shopping__cart__table">
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
                                <td class="product__cart__item">
                                    <div class="product__cart__item__pic">
                                        <img src="<c:url value="${item.productVariant.imageUrl}"/>" alt="${item.productVariant.name}">
                                    </div>
                                    <div class="product__cart__item__text">
                                        <h6>${item.productVariant.name}</h6>
                                        <h6>Size: ${item.productVariant.size}</h6>
                                        <h6>Size: ${item.productVariant.color}</h6>
                                    </div>
                                </td>
                                <td class="quantity__item">
                                    <div class="quantity">
                                        <div class="pro-qty-2">
                                            <input type="text" value="${item.quantity}">
                                        </div>
                                    </div>
                                </td>
                                <td class="cart__price">$ ${item.productVariant.price * item.quantity}</td>
                                <td class="cart__close">
                                    <form action="/xoa-gio-hang" method="post">
                                        <input type="hidden" id="productVariantId" name="productVariantId" value="${item.productVariant.id}">
                                        <button type="submit"><i class="fa fa-close"></i></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="continue__btn">
                            <a href="<c:url value='/san-pham?action=product_list' />">Continue Shopping</a>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                        <div class="continue__btn update__btn">
                            <a href="#"><i class="fa fa-spinner"></i> Update cart</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="cart__discount">
                    <h6>Discount codes</h6>
                    <form action="#">
                        <input type="text" placeholder="Coupon code">
                        <button type="submit">Apply</button>
                    </form>
                </div>
                <div class="cart__total">
                    <h6>Cart total</h6>
                    <ul>
                        <li>Subtotal <span>$ 0</span></li>
                        <li>Total <span>$ ${sessionScope.totalPrice}</span></li>
                    </ul>
                    <a href="<c:url value='/' />" class="primary-btn">Proceed to checkout</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Shopping Cart Section End -->