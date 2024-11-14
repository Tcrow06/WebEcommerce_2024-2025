<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <button class="btn btn-primary" data-mdb-toggle="modal" data-mdb-target="#createDiscountModal">+ Tạo</button>
        </div>

        <!-- Tabs -->
        <ul class="nav nav-tabs mb-3" role="tablist">
            <li class="nav-item btn-primary">
                <a class="nav-link active" data-mdb-toggle="tab" href="#ongoing">Đang diễn ra</a>
            </li>
            <li class="nav-item btn-primary">
                <a class="nav-link" data-mdb-toggle="tab" href="#upcoming">Sắp diễn ra</a>
            </li>
            <li class="nav-item btn-primary">
                <a class="nav-link" data-mdb-toggle="tab" href="#ended">Đã kết thúc</a>
            </li>
        </ul>

        <!-- Bảng mã giảm giá -->
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Tên</th>
                    <th>Code</th>
                    <th>Người mua mục tiêu</th>
                    <th>Giảm giá</th>
                    <th>Tổng số mã giảm giá có thể sử dụng</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>SFP-429528632672256<br>gfhfrtr</td>
                    <td>Mã giảm giá toàn Shop</td>
                    <td>Tất cả Người mua</td>
                    <td>₫5,000</td>
                    <td>5000</td>
                    <td>
                        <span class="badge bg-success">Đang diễn ra</span><br>
                        22:17 16/08/2022 - 22:25 16/02/2023
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="createDiscountModal" tabindex="-1" aria-labelledby="createDiscountModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createDiscountModalLabel">Chọn loại mã giảm giá</h5>
                <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="d-grid gap-2">
                    <a href="/aadmin/giam-gia-cho-san-pham" class="btn btn-primary">Thêm giảm giá sản phẩm</a>
                    <a href="/aadmin/giam-gia-cho-don-hang" class="btn btn-primary">Thêm giảm giá theo đơn hàng</a>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-mdb-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>