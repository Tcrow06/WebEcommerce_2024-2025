<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
    .table td, .table th {
        white-space: normal; /* Cho phép ngắt dòng */
        word-wrap: break-word; /* Tự động ngắt dòng khi quá dài */
        overflow-wrap: anywhere; /* Hỗ trợ ngắt dòng cho các từ quá dài */
    }

    .table {
        table-layout: auto; /* Điều chỉnh bảng để tự động phân bổ chiều rộng */
    }

    .rounded-circle {
        object-fit: cover; /* Đảm bảo ảnh luôn vừa trong hình tròn */
    }

    .badge-success {
        white-space: nowrap; /* Giữ nguyên kích thước của badge nếu cần */
    }

    /* Đặt độ rộng cố định cho cột "Tên" */
    .table .col-name {
        max-width: 200px; /* Đặt độ rộng cố định (có thể tùy chỉnh) */
    }

    .fw-bold {
        white-space: normal; /* Cho phép xuống dòng */
        word-wrap: break-word; /* Tự động ngắt dòng */
        overflow-wrap: anywhere; /* Hỗ trợ ngắt từ dài */
        max-width: 150px; /* Đặt chiều rộng tối đa (tùy chỉnh theo ý bạn) */
    }

    /* Giới hạn chiều rộng phần chứa thông tin */
    .ms-3 {
        max-width: 200px; /* Đặt chiều rộng tối đa cho container */
    }
</style>



<div class="content" style="background-color: white; min-height: 600px;">
    <div class="page-header">
        <div class="page-title">
            <h4>Quản lý mã giảm giá</h4>
            <h6>Marketing</h6>
        </div>
    </div>

    <div class="container my-5">
        <!-- Phần tiêu đề -->
        <h3 class="mb-4">Mã Giảm Giá Của Shop - Giảm giá cho từng đơn hàng</h3>

        <!-- Danh sách mã giảm giá -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h5 class="m-0">Danh sách mã giảm giá</h5>




            <!-- Nút Tạo với modal -->
            <a class="btn btn-primary" href="tao-giam-gia-cho-don-hang">+ Tạo</a>
        </div>

        <!-- Tabs -->
        <ul class="nav nav-tabs mb-3" role="tablist">
            <li class="nav-item btn-primary">
                <a class="nav-link
                <c:if test="${type == 'dang-dien-ra'}">
                            active
                </c:if>
                " href="?type=dang-dien-ra">Đang diễn ra</a>
            </li>
            <li class="nav-item btn-primary">
                <a class="nav-link
                <c:if test="${type == 'sap-dien-ra'}">
                            active
                </c:if>
                " href="?type=sap-dien-ra">Sắp diễn ra</a>
            </li>
            <li class="nav-item btn-primary">
                <a class="nav-link
                <c:if test="${type == 'da-ket-thuc'}">
                            active
                </c:if>
                " href="?type=da-ket-thuc">Đã kết thúc</a>
            </li>
        </ul>

        <!-- Bảng mã giảm giá -->
        <table class="table align-middle mb-0 bg-white">
            <thead class="bg-light">
            <tr>
                <th class="col-name">Tên</th> <!-- Thêm lớp col-name -->
                <th>Mức giảm</th>
                <th>Thời gian</th>
                <th>Đối tượng hướng đến</th>
                <th>Loại giảm giá</th>
                <th>Số tiền giảm tối đa</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${model}">
                <tr>
                    <td class="col-name"> <!-- Thêm lớp col-name -->
                        <div class="d-flex align-items-center">
                            <img
                                    src="https://www.shutterstock.com/image-illustration/red-price-tag-label-percentage-600nw-1947684382.jpg"
                                    alt="th"
                                    style="width: 45px; height: 45px"
                                    class="rounded-circle"
                            />
                            <div class="ms-3">
                                <p class="fw-bold mb-1">${item.name}</p> <!-- Tự xuống dòng -->
                                <p class="text-muted mb-0">${item.code}</p>
                            </div>
                        </div>
                    </td>
                    <td>
                        <p class="fw-normal mb-1">${item.discountPercentage} %</p>
                    </td>
                    <td>
                        <c:if test="${type == 'dang-dien-ra'}">
                            <span class="badge badge-success rounded-pill d-inline">Đang diễn ra</span>
                        </c:if>
                        <c:if test="${type == 'sap-dien-ra'}">
                            <span class="badge badge-danger rounded-pill d-inline">Sắp diễn ra</span>
                        </c:if>
                        <c:if test="${type == 'da-ket-thuc'}">
                            <span class="badge badge-secondary rounded-pill d-inline">Đã kết thúc</span>
                        </c:if>

                        <p class="text-muted mb-0">Từ ${item.getStringStartDate()}</p>
                        <p class="text-muted mb-0">Đến ${item.getStringEndDate()} </p>
                    </td>
                    <td>
                        <c:if test="${item.loyaltyPointsRequired == 0}">
                            Tất cả khách hàng
                        </c:if>
                        <c:if test="${item.loyaltyPointsRequired != 0}">
                            Khách hàng có điểm trên ${item.loyaltyPointsRequired}
                        </c:if>
                    </td>
                    <td>
                        <p class="text-muted mb-0">Giảm giá cho đơn hàng</p>
                        <p class="text-muted mb-0">trên ${item.minimumInvoiceAmount} VND</p>
                    </td>
                    <td>
                            ${item.maximumAmount} VND
                    </td>
                    <td>
                        <a href="chinh-sua-giam-gia-cho-don-hang?id=${item.id}" type="button" class="btn btn-link btn-sm btn-rounded">
                            Chỉnh sửa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>