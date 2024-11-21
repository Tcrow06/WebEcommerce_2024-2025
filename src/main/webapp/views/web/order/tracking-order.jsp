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
        /*border: 1px solid #ddd;*/
        /*border-radius: 5px;*/
        /*padding: 15px;*/
        /*margin-bottom: 10px;*/
        /*background-color: white;*/

        /*display: flex;*/
        /*align-items: center;*/
        /*justify-content: space-between;*/

        border: 1px solid #ddd;
        border-radius: 10px;
        padding: 20px;
        margin-bottom: 15px;
        background-color: #ffffff;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Thêm bóng */
        display: flex;
        align-items: center;
        justify-content: space-between;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    .order-item:hover {
        transform: translateY(-5px); /* Hiệu ứng nổi lên khi hover */
        box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
    }

    .order-info {
        flex: 3;
        padding-left: 15px; /* Thêm khoảng cách cho nội dung */
        font-size: 15px; /* Font chữ chuyên nghiệp hơn */
        line-height: 1.6;
    }

    .order-image {
        flex: 1; /* Chiếm 1 phần */
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .order-info strong {
        color: #e60023; /* Nhấn mạnh thông tin */

    }

    .order-image img {
        /* width: 100%;
        height: auto;
        max-width: 120px;
        border-radius: 5px;  */
        width: 100px;        /* Đặt chiều rộng ảnh là 100px */
        height: 100px;       /* Đặt chiều cao ảnh là 100px */
        object-fit: cover;   /* Đảm bảo ảnh sẽ được cắt và giữ tỷ lệ 100x100px mà không bị méo */
        border-radius: 10%;
    }

    .order-actions {
        flex: 1; /* Chiếm 1 phần */
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .order-actions a {
        /*display: inline-block;*/
        /*text-decoration: none;*/
        /*color: white;*/
        /*background-color: black;*/
        /*padding: 10px 20px;*/
        /*border-radius: 5px;*/
        /*font-size: 14px;*/
        /*transition: background-color 0.3s ease;*/

        display: inline-block;
        text-decoration: none;
        color: #fff; /* Màu chữ trắng */
        background-color: #343a40; /* Màu xám đen */
        padding: 12px 25px; /* Kích thước nút rộng hơn */
        border-radius: 8px; /* Bo góc mềm mại hơn */
        font-size: 16px; /* Kích thước chữ lớn hơn một chút */
        font-weight: bold; /* Chữ đậm */
        transition: background-color 0.3s ease, transform 0.3s ease; /* Hiệu ứng chuyển đổi mượt */
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Thêm bóng mờ */
    }

    .order-actions a:hover {
        background-color: #495057; /* Màu sáng hơn khi hover */
        transform: translateY(-3px); /* Hiệu ứng nhấc nút khi hover */
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
    }

    .no-order-message {
        font-size: 24px;
        color: #666;
        text-align: center;
        font-weight: bold;
        padding: 20px;
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
        <a href="#" id="processed" onclick="showOrders('PROCESSED')">Đã xử lý</a>
        <a href="#" id="received" onclick="showOrders('RECEIVED')">Đã nhận</a>
        <a href="#" id="cancelled" onclick="showOrders('CANCELLED')">Đã hủy</a>
    </div>

    <!-- Nội dung đơn hàng -->
    <div class="order-content">
        <div id="no-orders-message" class="no-order-message" style="display: none;">
            Không có đơn hàng
        </div>

        <c:forEach var="order" items="${orders}">
            <div class="order-item" data-status="${order.status}">
                <div class="order-image">
                    <img src="<c:url value='/api-image?path=${order.imgUrl}'/>"  alt="Hình ảnh đơn hàng ${order.id}">
                </div>
                <div class="order-info">
                    <strong>Mã đơn hàng:</strong> ELV${order.id} <br>
                    <strong>Ngày tháng:</strong> ${order.dateTime} <br>
                    <strong>Tổng giá trị:</strong> ${order.totalOrder} VND <br>
                    <strong>Tổng số lượng sản phẩm:</strong> ${order.allQuantity} sản phẩm
                </div>
                <div class="order-actions">
                    <a href="/trang-chu/don-hang/danh-sach-don-hang?id=${order.id}" target="_blank">Xem chi tiết</a>
                </div>

            </div>
        </c:forEach>

    </div>
</section>

<script>
    function showOrders(status) {
        const orders = document.querySelectorAll('.order-item');
        const noOrdersMessage = document.getElementById('no-orders-message');
        let hasOrders = false;

        if (status === 'ALL') {
            orders.forEach(order => order.style.display = 'flex');
            hasOrders = true;
        } else {
            orders.forEach(order => {
                if (order.getAttribute('data-status') === status) {
                    order.style.display = 'flex';
                    hasOrders = true;
                } else {
                    order.style.display = 'none';
                }
            });
        }

        if (!hasOrders) {
            noOrdersMessage.style.display = 'block';
        } else {
            noOrdersMessage.style.display = 'none';
        }

        const links = document.querySelectorAll('.order-status-bar a');
        links.forEach(link => link.classList.remove('active'));
        document.getElementById(status.toLowerCase()).classList.add('active');
    }

    document.addEventListener('DOMContentLoaded', () => {
        showOrders('ALL');
    });

</script>
