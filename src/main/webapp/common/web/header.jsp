<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__option">
        <div class="offcanvas__links">
            <a href="#">Sign in</a>
            <a href="#">FAQs</a>
        </div>
        <div class="offcanvas__top__hover">
            <span>Usd <i class="arrow_carrot-down"></i></span>
            <ul>
                <li>USD</li>
                <li>EUR</li>
                <li>USD</li>
            </ul>
        </div>
    </div>
    <div class="offcanvas__nav__option">
        <a href="#" class="search-switch"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a>
        <a href="#"><img src="<c:url value="/static/img/icon/heart.png"/>" alt=""></a>
        <a href="#"><img src="<c:url value="/static/img/icon/cart.png"/>" alt=""> <span>0</span></a>
        <div class="price">$0.00</div>
    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas__text">
        <p>Free shipping, 30-day return or refund guarantee.</p>
    </div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    /* Tạo viền mềm và màu đen xám cho dropdown */
    .custom-dropdown-menu {
        width: 220px; /* Tăng độ rộng của dropdown */
        padding: 0.5rem;
        background-color: #fff;
        border: 1px solid #b0b0b0; /* Viền màu đen xám */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15); /* Bóng mờ mềm */
        border-radius: 8px; /* Bo tròn các góc */
    }

    .custom-dropdown-menu .dropdown-item {
        color: #333;
        transition: background-color 0.2s, color 0.2s;
        padding: 10px 15px;
    }

    .custom-dropdown-menu .dropdown-item:hover {
        background-color: #f0f0f5;
        color: #000;
    }

    .custom-dropdown-menu .dropdown-divider {
        margin: 0.4rem 0;
    }

    .custom-dropdown-menu .dropdown-item.text-danger:hover {
        background-color: #f8d7da;
        color: #dc3545;
    }

    /* Thay đổi màu khi click vào avatar */
    #userDropdown .user-avatar {
        cursor: pointer;
        transition: box-shadow 0.3s;
    }

    #userDropdown .user-avatar:hover {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

</style>
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7">
                    <div class="header__top__left">
                        <p>Free shipping, 30-day return or refund guarantee.</p>
                    </div>
                </div>
                <div class="col-lg-6 col-md-5">
                    <div class="header__top__right">
                        <div class="header__top__links">
                            <a href="<c:url value="/dang-nhap"/>">Sign in</a>
                            <a href="#">FAQs</a>
                        </div>
                        <div class="header__top__hover">
                            <span>Usd <i class="arrow_carrot-down"></i></span>
                            <ul>
                                <li>USD</li>
                                <li>EUR</li>
                                <li>USD</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="header__logo">
                    <a href="<c:url value="/trang-chu"/>"><img src="<c:url value="/static/img/logo.png"/>" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="active"><a href="<c:url value="/trang-chu"/>">Home</a></li>
                        <li><a href="<c:url value="/danh-sach-san-pham"/>">Shop</a></li>
                        <li><a href="#">Pages</a>
                            <ul class="dropdown">
                                <li><a href="<c:url value="/ve-chung-toi"/>">About Us</a></li>
                                <li><a href="<c:url value="/san-pham?action=ten-san-pham-o-day"/>">Shop Details</a></li>
                                <li><a href="<c:url value="/gio-hang"/>">Shopping Cart</a></li>
                                <li><a href="<c:url value="/thanh-toan"/>">Check Out</a></li>
                            </ul>
                        </li>
                        <li><a href="<c:url value="/blog"/>">Blog</a></li>
                        <li><a href="<c:url value="/ve-chung-toi"/>">Contacts</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3 col-md-3">
                <div class="header__nav__option">
                    <a href="#" class="search-switch"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a>
                    <a href="<c:url value="/thong-bao"/>"><img src="<c:url value="/static/img/icon/bell.png"/>" alt=""></a>
                    <a href="<c:url value="/gio-hang"/>"><img src="<c:url value="/static/img/icon/cart.png"/>" alt=""> <span>0</span></a>
                    <div class="price">$0.00</div>
                </div>

                <c:if test="${not empty user}">
                    <div class="header__menu mobile-menu">
                        <ul class="d-flex align-items-center">
                            <li>
                                <a href="#" id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                    <c:if test="${not empty user.avatar}">
                                        <img src="${user.avatar}" alt="User Profile" width="32" height="32" class="rounded-circle user-avatar">
                                    </c:if>
                                    <c:if test="${empty user.avatar}">
                                        <img src='<c:url value="/static/img/avatar/user.png"/>' alt="User Profile" width="32" height="32" class="rounded-circle user-avatar">
                                    </c:if>
                                </a>

                                <ul class="dropdown-menu custom-dropdown-menu" aria-labelledby="userDropdown">
                                    <li><a class="dropdown-item" href="<c:url value="/trang-chu/thong-tin-ca-nhan"/>">Thông tin tài khoản</a></li>
                                    <li><a class="dropdown-item" href="#">Đơn hàng của tôi</a></li>
                                    <li><a class="dropdown-item" href="#">Danh sách yêu thích</a></li>
                                    <li><a class="dropdown-item" href="#">Cài đặt</a></li>
                                    <hr class="dropdown-divider">
                                    <li><a class="dropdown-item text-danger" href="#">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:if>

            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>
<!-- Header Section End -->