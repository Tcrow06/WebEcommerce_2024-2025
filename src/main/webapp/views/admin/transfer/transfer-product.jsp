<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Thông tin chi tiết của đơn hàng hoàn/trả</h4>
            <h6>Thông tin của người dùng và sản phẩm ở bên dưới. Vui lòng xác nhận!</h6>
        </div>
    </div>

    <!-- Sender Information -->
    <div class="card mb-4">
        <div class="card-header">
            <h5>Thông tin người mua</h5>
        </div>
        <div class="card-body">
            <form>
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>ID</label>
                            <input type="text" class="form-control" value="${customer.id}" readonly>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Họ tên</label>
                            <input type="text" class="form-control" value="${customer.name}" readonly>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Số điện thoại</label>
                            <input type="text" class="form-control" value="${customer.phone}" readonly>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" value="${customer.email}" readonly>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Product Return Details -->
    <div class="card">
        <div class="card-header">
            <h5>Thông tin chi tiết sản phẩm hoàn trả</h5>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/chu-cua-hang/danh-sach-tra" method="post">
                <div class="row">
                    <!-- Left Side: Product Image and Name -->
                    <div class="col-md-4 text-center">
                        <label>${productReturn.productName}</label>
                        <div class="form-group">S
                            <div>
                                <img src="${productReturn.imgUrl}" alt="Product Image" class="img-fluid"
                                     style="max-width: 100%; height: auto;">
                            </div>
                        </div>
                    </div>

                    <!-- Right Side: Product Details -->
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Ngày hoàn/trả</label>
                                    <input type="text" class="form-control" value="${productReturn.returnDate}" readonly>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Số lượng trả</label>
                                    <input type="number" class="form-control" value="${productReturn.quantityReturn}" readonly>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Màu</label>
                                    <input type="text" class="form-control" value="${productReturn.color}" readonly>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Giá sản phẩm</label>
                                    <input type="text" class="form-control" value="${productReturn.salePrice}" readonly>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Lý do hoàn/trả</label>
                                    <input type="text" class="form-control" value="${productReturn.reason}" readonly>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Size</label>
                                    <input type="text" class="form-control" value="${productReturn.size}" readonly>
                                </div>
                            </div>
                            <input type="hidden" name="orderDetailReturnId" value="${productReturn.id}">
                            <input type="hidden" name="orderDetailReturnQuantity" value="${productReturn.quantityReturn}">
                        </div>
                    </div>
                </div>
                <div class="text-end mt-4">
                    <c:if test="${productReturn.status == 0}">
                        <button type="submit" class="btn btn-submit me-2" name="action" value="return">Hoàn trả</button>
                        <button type="submit" class="btn btn-submit me-2" name="action" value="noReturn" style="background-color: red">Không hoàn trả</button>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/chu-cua-hang/danh-sach-tra" class="btn btn-cancel">Hủy</a>
                </div>
            </form>
        </div>
    </div>
</div>

