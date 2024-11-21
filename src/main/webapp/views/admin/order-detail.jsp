<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style>
    .content {
        margin: 20px;
        font-family: Arial, sans-serif;
    }

    .page-header {
        background-color: #ff944d;
        padding: 10px 20px;
        color: white;
        border-radius: 5px;
        margin-bottom: 20px;
    }

    .page-title h4, .page-title h6 {
        margin: 0;
    }

    .table-styled {
        width: 100%;
        border-collapse: collapse;
        background-color: white;
        overflow: hidden;
        border: 1px solid #ff944d;
    }

    .table-styled thead tr {
        background-color: #ff944d;
        color: white;
        text-align: center;
    }

    .table-styled th {
        padding: 12px;
        text-transform: uppercase;
        font-weight: bold;
    }

    .table-styled td {
        padding: 12px;
        text-align: center;
        border-bottom: 1px solid #f5d7b0;
    }

    .table-styled tbody tr:nth-child(odd) {
        background-color: #fff7e6;
    }

    .table-styled tbody tr:nth-child(even) {
        background-color: #ffe6cc;
    }

    .table-styled tbody tr:hover {
        background-color: #ffcc99;
        cursor: pointer;
    }

    /* Hình ảnh */
    .product-image {
        width: 100px;
        height: 100px;
        object-fit: cover;
        border-radius: 5px;
        border: 2px solid #ff944d;
    }

    .card {
        border: 1px solid #ff944d;
        border-radius: 10px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .card-body {
        padding: 20px;
    }
</style>

<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Chi tiết đơn hàng</h4>
            <h6>Quản lý chi tiết các mặt hàng trong đơn hàng</h6>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Hình ảnh</th>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng </th>
                            <th>Giá</th>
                            <th>Màu</th>
                            <th>Size</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${orderItemList}">
                            <tr>
                                <td>
                                    <img
                                            src="<c:url value='/api-image?path=${item.imageUrl}'/>"
                                            alt="Product Image"
                                            class="product-image"
                                            style="width: 100px; height: 100px; object-fit: cover;">
                                </td>
                                <td>${item.productName}</td>
                                <td>${item.quantity}</td>
                                <td>${item.price}</td>
                                <td>${item.color}</td>
                                <td>${item.size}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


