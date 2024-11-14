<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
    .page-wrapper {
        padding: 20px;
        padding-top: 60px;
    }
</style>

<div class="content" style="background-color: white; min-height: 600px;">
    <div class="page-header">
        <div class="page-title">
            <h4>Quản lý mã giảm giá</h4>
            <h6>Marketing</h6>
        </div>
    </div>

    <div class="product-discount-list">
        <!-- Phần tiêu đề -->
        <h3 class="mb-4">Mã Giảm Giá Của Shop - Giảm giá cho từng sản phẩm</h3>

        <!-- Danh sách mã giảm giá -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h5 class="m-0">Danh sách giảm giá</h5>




            <!-- Nút Tạo với modal -->
            <a class="btn btn-primary" href="/chu-cua-hang/tao-giam-gia-cho-san-pham">+ Tạo</a>
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
        <div class="productList">



            <div class="row mb-4 d-flex align-items-center">
                <div class="col-md-2 col-lg-2 col-xl-2">
                    <img src="https://m.media-amazon.com/images/I/81NmdZFyncL._UF350,350_QL50_.jpg" class="img-fluid rounded-3" alt="Cotton T-shirt">
                </div>
                <div class="col-md-3 col-lg-3 col-xl-3">
                    <h6 class="text-muted fw-bold">Tên khuyến mãi</h6>
                    <h6 class="mb-0">Tên sản phẩm</h6>
                    <h6 class="mb-0">
                        <span style="text-decoration: line-through; color: grey;">7,99 $</span>
                        5,99 $
                    </h6>
                </div>
                <div class="col-md-3 col-lg-3 col-xl-2">
                    <span class="badge bg-danger">Đang diễn ra</span><br>
                    22:17 16/08/2022 - 22:25 16/02/2023
                </div>
                <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1 text-end"> <!-- Thêm text-end -->
                    <button type="submit" class="btn btn-dark add-to-cart" >Chỉnh sửa</button>
                </div>
            </div>


        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>