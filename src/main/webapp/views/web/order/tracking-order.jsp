<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<style>
    body {
        margin: 0;
        font-family: 'Arial', sans-serif;
        background-color: #f3f4f7;
        color: #333;
    }

    section {
        padding: 20px;
    }

    .order-status-bar {
        display: flex;
        justify-content: space-around;
        background-color: black;
        padding: 15px 0;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }

    .order-status-bar a {
        text-decoration: none;
        color: #fff;
        padding: 12px 20px;
        font-size: 16px;
        font-weight: bold;
        transition: all 0.3s ease;
        border-radius: 8px;
    }

    .order-status-bar a.active {
        color: black;
        background-color: #fff;
        border-bottom: none;
        transform: scale(1.1);
    }

    .order-status-bar a:hover {
        color: black;
        background-color: #fff;
        transform: scale(1.05);
    }

    .order-content {
        padding: 30px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        margin-top: 20px;
    }

    .order-item {
        border: 1px solid #ddd;
        border-radius: 12px;
        padding: 20px;
        margin-bottom: 15px;
        background-color: #ffffff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        display: flex;
        align-items: center;
        justify-content: space-between;
        transition: all 0.3s ease;
    }

    .order-item:hover {
        transform: translateY(-5px);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        background-color: #f9f9f9;
    }

    .order-info {
        flex: 3;
        padding-left: 15px;
        font-size: 15px;
        line-height: 1.6;
    }

    .order-image {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .order-info strong {
        color: #e60023;
    }

    .order-image img {
        width: 100px;
        height: 100px;
        object-fit: cover;
        border-radius: 10%;
        border: 2px solid #ddd;
    }

    .order-actions {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .order-actions a {
        display: inline-block;
        text-decoration: none;
        color: #fff;
        background-color: #343a40;
        padding: 12px 25px;
        border-radius: 8px;
        font-size: 16px;
        font-weight: bold;
        transition: background-color 0.3s ease, transform 0.3s ease;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .order-actions a:hover {
        background-color: #495057;
        transform: translateY(-3px);
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
    }

    .no-order-message {
        font-size: 24px;
        color: #666;
        text-align: center;
        font-weight: bold;
        padding: 20px;
        display: none;
    }

    .order-content {
        padding: 20px;
    }

    section.shop.spad {
        padding-top: 0;
        margin-top: 0;
    }

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
                    <a href="/trang-chu/don-hang/danh-sach-don-hang?id=${order.id}">Xem chi tiết</a>
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
