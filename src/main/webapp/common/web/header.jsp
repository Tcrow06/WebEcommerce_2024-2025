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
                <div class="col-lg-12">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="header__top__left">
                            <c:if test="${not empty user}">
                                <p>Chào mừng ${user.name} đến với Eleven Store</p>
                            </c:if>
                        </div>
                        <div class="header__top__right d-flex align-items-center">
                            <div class="header__top__links">
                                <c:if test="${not empty user}">
                                    <form action="<c:url value='/dang-xuat'/>" method="post" class="d-inline">
                                        <button class="btn-dang-xuat btn btn-link" type="submit">Đăng xuất</button>
                                    </form>
                                </c:if>
                                <c:if test="${empty user}">
                                    <a href="<c:url value='/dang-nhap'/>">Đăng nhập</a>
                                </c:if>
                                <a href="#">FAQs</a>
                            </div>
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
                        <li><a href="<c:url value="/danh-sach-san-pham?page=1&maxPageItem=9"/>">Shop</a></li>
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
            <div class="col-lg-3 col-md-3 d-flex justify-content-end align-items-center gap-4" >
                <div class="header__nav__option">
                    <a href="#" class="search-switch"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a>
                </div>
                <div class="header__nav__option">
                    <a href="<c:url value="/thong-bao"/>"><img src="<c:url value="/static/img/icon/bell.png"/>" alt=""> </a>
                </div>
                <div class="header__nav__option">
                    <a href="<c:url value="/gio-hang"/>"><img src="<c:url value="/static/img/icon/cart.png"/>" alt=""> <span>0</span></a>
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