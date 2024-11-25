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

        <c:if test="${model != null}">

        </c:if>


        <!-- Phần tiêu đề -->
        <h3 class="mb-4">Mã Giảm Giá Của Shop - Giảm giá cho từng sản phẩm</h3>

        <!-- Danh sách mã giảm giá -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h5 class="m-0">Danh sách giảm giá</h5>




            <!-- Nút Tạo với modal -->
            <a class="btn btn-primary" href="/chu-cua-hang/tao-giam-gia-cho-san-pham">+ Tạo</a>
        </div>

        <!-- Tabs -->
        <ul class="nav nav-tabs mb-3">
            <li class="nav-item btn-primary">
                <a class="nav-link
                    <c:if test="${dangdienra == true}">
                            active
                    </c:if>
                    " href="/chu-cua-hang/giam-gia-cho-san-pham">Đang diễn ra</a>
            </li>
            <li class="nav-item btn-primary">
                <a class="nav-link
                <c:if test="${dangdienra == false}">
                            active
                </c:if>
                " href="?type=sap-dien-ra">Sắp diễn ra</a>
            </li>
        </ul>

        <!-- Bảng mã giảm giá -->
        <div class="productList">

            <c:forEach var="item" items="${model}">
                <div class="row mb-4 d-flex align-items-center">
                    <div class="col-md-2 col-lg-2 col-xl-2">
                        <c:if test="${item.product != null}">
                            <img src="<c:url value='/api-image?path=${item.product.photo}'/>" class="img-fluid rounded-3" alt="Sản phảm">
                        </c:if>
                        <c:if test="${item.product == null}">
                            <img src="<c:url value='/api-image?path=404'/>" class="img-fluid rounded-3" alt="Sản phảm">
                        </c:if>
                    </div>
                    <div class="col-md-3">
                        <h6 class="text-muted fw-bold">${item.name}</h6>
                        <c:if test="${item.product != null}">
                            <h6 class="mb-0">${item.product.name}</h6>
                        </c:if>
                        <h6 class="mb-0">

                            <c:if test="${item.product != null}">
                                <p class="mb-0" style="font-weight: 500;">${item.product.getDiscountedPrice()}</p>
                                <span style="text-decoration: line-through; color: grey;">${item.product.price}</span>
                            </c:if>
<%--                            </c:if>--%>

<%--                            <c:if test="${item.product != null}">--%>
<%--                                <span style="text-decoration: line-through; color: grey;">${item.product.getDiscountedPrice()}</span>--%>
<%--                            </c:if>--%>
                            Giảm giá ${item.discountPercentage} %
                        </h6>
                    </div>
                    <div class="col-md-3">
                        <c:if test="${dangdienra == true}">
                            <span class="badge bg-success">Đang diễn ra</span><br>
                        </c:if>
                        <c:if test="${dangdienra == false}">
                            <span class="badge bg-danger">Sắp diễn ra</span><br>
                        </c:if>
                        ${item.getStringStartDate()} - ${item.getStringEndDate()}
                    </div>
                    <div class="col-md-3 offset-lg-1 text-end"> <!-- Thêm text-end -->
                        <a href="chinh-sua-giam-gia-san-pham?id=${item.id}" class="btn btn-dark add-to-cart" >Chỉnh sửa</a>
                    </div>
                </div>

            </c:forEach>


        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>