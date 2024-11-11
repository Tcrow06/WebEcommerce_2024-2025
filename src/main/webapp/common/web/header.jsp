<!-- Header Section Begin -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    .container .dropdown-item {
        position: relative;
        text-decoration: none;
    }

    .container .dropdown-item.selected::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: 0;
        width: 100%;
        height: 2px;
        background-color: #000;
    }

    .container .dropdown-item:hover::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: 0;
        width: 100%;
        height: 2px;
        background-color: #ddd;
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
                        <li><a href="./contact.html">Contacts</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3 col-md-3 d-flex justify-content-end align-items-center gap-4" >
                <div class="header__nav__option">
                    <a href="#" class="search-switch"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a>
                </div>
                <div class="header__nav__option">
                    <a href="<c:url value="/gio-hang"/>"><img src="<c:url value="/static/img/icon/cart.png"/>" alt=""> <span>0</span></a>
                </div>
<%--                <c:if test="${not empty user}">--%>
<%--                    <div class="header__menu mobile-menu">--%>
<%--                        <ul class="d-flex align-items-center">--%>
<%--                            <li>--%>
<%--                                <a href="#">--%>
<%--                                    <c:if test="${not empty user.avatar}">--%>
<%--                                        <img src="${user.avatar}" alt="mdo" width="32" height="32" class="rounded-circle">--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${empty user.avatar}">--%>
<%--                                        <img src='<c:url value = "/static/img/avatar/user.png"/>' alt="mdo" width="32" height="32" class="rounded-circle">--%>
<%--                                    </c:if>--%>
<%--                                </a>--%>
<%--                                <ul class="dropdown" style="width:165px">--%>
<%--                                    <li><a href="<c:url value="#"/>">Thông tin cá nhân</a></li>--%>
<%--                                    <li><a href="<c:url value="#"/>">Lịch sử mua hàng</a></li>--%>
<%--                                </ul>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </c:if>--%>

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
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown" style="width:165px">
                                    <li><a class="dropdown-item" href="#">Thông tin tài khoản</a></li>
                                    <li><a class="dropdown-item" href="#">Đơn hàng của tôi</a></li>
                                    <li><a class="dropdown-item" href="#">Danh sách yêu thích</a></li>
                                    <li><a class="dropdown-item" href="#">Cài đặt</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="#">Đăng xuất</a></li>
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