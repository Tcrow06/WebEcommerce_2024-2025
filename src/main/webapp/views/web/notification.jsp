<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
    body {
        background-color: #f5f5f5;
        font-family: Arial, sans-serif;
    }

    .shop-info {
        display: flex;
        align-items: center;
        gap: 10px;
        padding-bottom: 15px;
        border-bottom: 1px solid #e0e0e0;
    }

    .shop-info button {
        font-size: 14px;
        padding: 5px 15px;
    }

    .product-info {
        display: flex;
        padding: 15px 0;
        border-bottom: 1px solid #e0e0e0;
    }

    .product-info img {
        width: 100px;
        height: 100px;
        border-radius: 8px;
        margin-right: 15px;
    }

    .product-details {
        flex: 1;
    }

    .action-buttons {
        display: flex;
        justify-content: center;
        gap: 10px;
        margin-top: 20px;
    }

    .action-buttons .btn {
        padding: 10px 30px;
        font-size: 16px;
        border-radius: 8px;
    }

     .text-clicked {
         opacity: 0.5; /* Làm mờ các đoạn văn bản */
     }
</style>

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
                    <div class="shop-info">
                        <span class="ms-auto text-success"><i class="bi bi-truck"></i> Giao hàng thành công</span>
                    </div>
                    <div class="row product-info">
                        <div class="col-md-flex">
                            <c:forEach var="img" items="${order.imgList}">
                                <img src= "<c:url value='/api-image?path=${img}'/>" alt="Product Image" class="img-thumbnail me-2" style="width: 100px; height: 100px;">
                            </c:forEach>
                        </div>
                        <div class="col-md-flex">
                            <div class="product-details">
                                <h5>Đơn hàng đã hoàn tất</h5>
                                <p>Đơn hàng <strong>ELV${order.id}</strong> đã hoàn tất. Hãy đánh giá sản phẩm để nhận 200 điểm và giúp người dùng khác hiểu hơn về sản phẩm nhé.</p>
                                <div class="row">
                                    <div class="col-lg-9">
                                        <p class="fw-light">
                                            <script>
                                                var orderDate = new Date("${order.date}");
                                                var formattedDate = orderDate.toLocaleDateString('en-GB');
                                                document.write(formattedDate);
                                            </script>
                                        </p>
                                    </div>
                                    <div class="col-lg-3 action-buttons">
                                        <a href="/trang-chu/don-hang/danh-sach-don-hang?id=${order.id}" class="btn btn-danger">Đánh giá</a>
                                        <a href="/san-pham?id=${order.id}" class="btn btn-secondary">Mua lại</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <!-- Thêm các thông báo khác nếu cần -->
    </div>

</section>
<!-- Breadcrumb Section End -->

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const notifications = document.querySelectorAll(".product-info");

        notifications.forEach(notification => {
            notification.addEventListener("click", function (event) {
                // Kiểm tra nếu phần tử được nhấp là các thành phần cần làm mờ
                const textElements = this.querySelectorAll("h5, p, .fw-light");
                textElements.forEach(el => el.classList.add("text-clicked"));
            });
        });
    });
</script>
