<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__text">
                    <h4>Notification</h4>
                    <div class="breadcrumb__links">
                        <a href="<c:url value="/trang-chu"/>">Home</a>
                        <span>Notification</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="side-bar">
    <div class="d-flex">
        <!-- Sidebar -->
        <div class="sidebar m-3">
            <div class="d-flex flex-column" style=" height:100%;">
                <nav class="nav flex-column">
                    <a class="link-offset-2 m-2 link-underline link-underline-opacity-0" href="<c:url value="/thong-bao"/>" style = "color:#f53d2d" >Cập Nhật Đơn Hàng</a>
                    <a class="link-offset-2 m-2 link-underline link-underline-opacity-0" href="<c:url value="/phieu-giam-gia"/>" style = "color:black" >Kho Voucher</a>
                </nav>
            </div>
        </div>

        <!-- Content -->
        <div class="container my-4">
            <c:if test="${not empty orders}">
                <c:forEach var="order" items="${orders}">
                <a href="<c:url value='/hoan-thanh'/>">
                <!-- Thông báo 1 -->
                    <div class="notification-item d-flex align-items-start m-3">
                        <img src="/static/img/product/product-1.jpg" alt="Thông báo" class="img-thumbnail me-2" style="height: 100%;width:9%">
                        <div class="flex-grow-1">
                            <div class="pl-2 mb-2 bg-dark-subtle text-dark-emphasis position-relative">
                                <div class="notification-title">Đơn hàng đã hoàn tất</div>
                                <div class="notification-desc">
                                    Đơn hàng <strong>${order.id}</strong> đã hoàn thành. Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!
                                    <div class="row text-muted mt-2 pl-1">
                                        <div class="col-9">${order.date}</div>
                                        <div class="col-3"><button type="button" class="btn btn-secondary">Đánh giá sản phẩm</button></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
                </c:forEach>
            </c:if>
        </div>
        <!-- Thêm các thông báo khác nếu cần -->
    </div>

</section>
<!-- Breadcrumb Section End -->