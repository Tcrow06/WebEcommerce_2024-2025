<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<style>
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        color: #333;
    }

    section {
        padding: 20px;
    }

    .order-status-bar {
        display: flex;
        justify-content: space-around;
        background-color: #fff;
        padding: 10px 0;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .order-status-bar a {
        text-decoration: none;
        color: #333;
        padding: 10px 15px;
        font-size: 16px;
        font-weight: bold;
    }

    .order-status-bar a.active {
        color: #e60023;
        border-bottom: 3px solid #e60023;
    }

    .order-status-bar a:hover {
        color: #e60023;
    }

    .order-content {
        padding: 20px;
    }

    section.shop.spad {
        padding-top: 0;
        margin-top: 0;
    }

    .order-content {
        padding: 20px;
        background-color: #f9f9f9;
        min-height: 100px;
    }

    .order-item {
        border: 1px solid #ddd;
        border-radius: 5px;
        padding: 15px;
        margin-bottom: 10px;
        background-color: white;

        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .order-info {
        flex: 3; /* Chiếm 3 phần */
    }

    .order-image {
        flex: 1; /* Chiếm 1 phần */
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .order-image img {
        /* width: 100%;
        height: auto;
        max-width: 120px;
        border-radius: 5px;  */
        width: 100px;        /* Đặt chiều rộng ảnh là 100px */
        height: 100px;       /* Đặt chiều cao ảnh là 100px */
        object-fit: cover;   /* Đảm bảo ảnh sẽ được cắt và giữ tỷ lệ 100x100px mà không bị méo */
    }

    .order-actions {
        flex: 1; /* Chiếm 1 phần */
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .order-actions a {
        display: inline-block;
        text-decoration: none;
        color: white;
        background-color: black;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 14px;
        transition: background-color 0.3s ease;
    }

    .order-actions a:hover {
        background-color: #333;
    }

    /* Responsive Styles */
    @media (max-width: 768px) {
        .steps {
            flex-direction: column;
            align-items: center;
        }

        .step {
            flex: 1 1 auto;
            max-width: none;
            margin-bottom: 20px;
        }

        .step .step-icon-wrap::before,
        .step .step-icon-wrap::after {
            display: none;
        }
    }

    @media (max-width: 480px) {
        .order-status {
            flex-direction: column;
            align-items: center;
        }

        .order-status div {
            flex: 1 1 100%;
            text-align: center;
            margin-bottom: 10px;
        }
    }
</style>

<section class="shop spad">
    <div class="order-status-bar">
        <a href="#" id="all" class="active" onclick="showOrders('ALL')">Tất cả</a>
        <a href="#" id="pending" onclick="showOrders('PENDING')">Chờ xác nhận</a>
        <a href="#" id="delivered" onclick="showOrders('DELIVERED')">Đã vận chuyển</a>
        <a href="#" id="waiting" onclick="showOrders('WAITING')">Đang xử lý</a>
        <a href="#" id="received" onclick="showOrders('RECEIVED')">Đã nhận</a>
        <a href="#" id="cancelled" onclick="showOrders('CANCELLED')">Đã hủy</a>
    </div>

    <!-- Nội dung đơn hàng -->
    <div class="order-content">
        <c:forEach var="order" items="${orders}">
            <div class="order-item" data-status="${order.status}">
                <div class="order-image">
                    <img src="${order.imgUrl}" alt="Hình ảnh đơn hàng ${order.id}">
                </div>
                <div class="order-info">
                    <strong>Mã đơn hàng:</strong> ELV${order.id} <br>
                    <strong>Ngày tháng:</strong> ${order.dateTime} <br>
                    <strong>Tổng giá trị:</strong> ${order.totalOrder} VND <br>
                    <strong>Tổng số lượng sản phẩm:</strong> ${order.allQuantity} sản phẩm
                </div>
                <div class="order-actions">
                    <a href="order-detail.html?id=${order.id}" target="_blank">Xem chi tiết</a>
                </div>
            </div>
        </c:forEach>

    </div>
</section>

<script>
    function showOrders(status) {
        // Lấy tất cả các đơn hàng
        const orders = document.querySelectorAll('.order-item');

        // Nếu trạng thái là 'all', hiển thị tất cả
        if (status === 'ALL') {
            orders.forEach(order => order.style.display = 'flex');
        } else {
            // Ẩn tất cả các đơn hàng
            orders.forEach(order => {
                // Lọc theo trạng thái
                if (order.getAttribute('data-status') === status) {
                    order.style.display = 'flex';  // Hiển thị đơn hàng có trạng thái khớp
                } else {
                    order.style.display = 'none';  // Ẩn các đơn hàng không khớp
                }
            });
        }

        // Cập nhật trạng thái của các liên kết thanh trạng thái
        const links = document.querySelectorAll('.order-status-bar a');
        links.forEach(link => link.classList.remove('active'));  // Xóa lớp active của tất cả các liên kết
        document.getElementById(status).classList.add('active');  // Thêm lớp active vào liên kết tương ứng
    }

    document.addEventListener('DOMContentLoaded', () => {
        showOrders('ALL'); // Hiển thị tất cả đơn hàng mặc định
    });

</script>
