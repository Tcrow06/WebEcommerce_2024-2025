<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="content" style="background-color: rgb(253, 251, 251);">
    <div class="page-header">
        <div class="page-title">
            <h4>Danh sách sản phẩm</h4>
            <h6>Quản lý các mặt hàng của bạn một cách dễ dàng !</h6>
        </div>
    </div>

    <div class="container my-5">

        <!-- Danh sách mã giảm giá -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h5 class="m-0">Danh sách sản phẩm</h5>
            <!-- Nút Tạo với modal -->
            <a class="btn btn-primary" href="them-san-pham">+ Thêm sản phẩm</a>
        </div>

        <!-- Tabs -->
        <ul class="nav nav-tabs mb-3" role="tablist">
            <li class="nav-item btn-primary">
                <a class="nav-link active" href="?type=dang-kinh-doanh">Đang kinh doanh</a>
            </li>
            <li class="nav-item btn-primary">
                <a class="nav-link" href="?type=ngung-kinh-doanh">Ngừng kinh doanh</a>
            </li>
        </ul>

        <div class="card">
            <div class="card-body">
                <div class="table-top">
                    <div class="search-set">
                        <div class="search-path">
                            <a class="btn btn-filter" id="filter_search">
                                <img src="https://w7.pngwing.com/pngs/403/20/png-transparent-computer-icons-filter-miscellaneous-angle-rectangle.png" alt="img">
                                <span><img src="https://w7.pngwing.com/pngs/403/20/png-transparent-computer-icons-filter-miscellaneous-angle-rectangle.png" alt="img"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="card mb-0" id="filter_inputs">
                    <div class="card-body pb-0">
                        <div class="row">
                            <div class="col-lg-12 col-sm-12">
                                <form action="danh-sach-san-pham" method="get">
                                    <div class="row">
                                        <input type="hidden" name="type" value="${type}">
                                        <div class="col-lg-2 col-sm-6 col-12">
                                        </div>
                                        <div class="col-lg-3 col-sm-6 col-12">
                                            <div class="form-group">
                                                <select class="select" name="category">
                                                    <option value="all">Tất cả</option>
                                                    <c:forEach var="item" items="${category}">
                                                        <option value="${item.code}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-sm-6 col-12">
                                            <div class="form-outline" data-mdb-input-init>
                                                <input type="text"  name="name" id="typeText" class="form-control" />
                                                <label class="form-label" for="typeText">Tên sản phẩm</label>
                                            </div>
                                        </div>

                                        <div class="col-lg-3 col-sm-6 col-12">
                                            <button type="submit" class="btn btn-primary">Lọc</button>
<%--                                            <a class="btn btn-primary" href="tao-giam-gia-cho-don-hang">Lọc</a>--%>
                                        </div>

                                        <div class="col-lg-2 col-sm-6 col-12">
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col" class="h5">Sản phẩm</th>
                            <th scope="col">Có giảm giá</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="item" items="${model}">
                            <tr>
                                <th scope="row">
                                    <div class="d-flex align-items-center">
                                        <img src="<c:url value='/api-image?path=${item.photo}'/>" class="img-fluid rounded-3"
                                             style="width: 120px;" alt="Book">
                                        <div class="flex-column ms-4">
                                            <p class="mb-2">${item.name}</p>
                                            <p class="mb-0">${item.brand}</p>
                                        </div>
                                    </div>
                                </th>
                                <td class="align-middle">

                                    <c:if test="${item.productDiscount != null}">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckCheckedDisabled_${item.id}" checked disabled/>
                                        <label class="form-check-label" for="flexCheckCheckedDisabled_${item.id}">Giảm_${item.productDiscount.discountPercentage}%</label>
                                        <span class="badge badge-${item.productDiscount.getBootstrapClassStatus()} rounded-pill d-inline">${item.productDiscount.getStatus()}</span>
                                    </c:if>
                                    <c:if test="${item.productDiscount == null}">
                                        <div class="flex-column ms-4">
                                            <p class="mb-2">Không giảm</p>
                                        </div>
                                    </c:if>
                                </td>
                                <td class="align-middle">
                                    <p class="mb-0" style="font-weight: 500;">${item.getDiscountedPrice()}</p>
                                    <c:if test="${item.productDiscount != null}">
                                        <span style="text-decoration: line-through; color: grey;">${item.price}</span>
                                    </c:if>
                                </td>
                                <td class="align-middle">
                                    <c:if test="${type == 'dang-kinh-doanh'}">
                                        <span class="badge badge-success rounded-pill d-inline">Đang kinh doanh</span>
                                    </c:if>
                                    <c:if test="${type == 'ngung-kinh-doanh'}">
                                        <span class="badge badge-danger rounded-pill d-inline">Ngừng kinh doanh</span>
                                    </c:if>
                                </td>
                                <td>
                                    <a href="chinh-sua-san-pham?id=${item.id}" type="button" class="btn btn-link btn-sm btn-rounded">
                                        Chỉnh sửa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>

