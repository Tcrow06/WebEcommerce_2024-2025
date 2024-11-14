<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Transfer List</h4>
            <h6>Transfer your stocks to one store another store.</h6>
        </div>
<%--        <div class="page-btn">--%>
<%--            <a href="addtransfer.html" class="btn btn-added"><img src="/static/admin/assets/img/icons/plus.svg" alt="img"--%>
<%--                                                                  class="me-2">Add Transfer</a>--%>
<%--        </div>--%>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="table-top">
                <div class="search-set">
                    <div class="search-path">
                        <a class="btn btn-filter" id="filter_search">
                            <img src="/static/admin/assets/img/icons/filter.svg" alt="img">
                            <span><img src="/static/admin/assets/img/icons/closes.svg" alt="img"></span>
                        </a>
                    </div>
                    <div class="search-input">
                        <a class="btn btn-searchset">
                            <img src="/static/admin/assets/img/icons/search-white.svg" alt="img">
                        </a>
                    </div>
                </div>
                <div class="wordset">
                    <ul>
                        <li>
                            <a data-bs-toggle="tooltip" data-bs-placement="top" title="pdf"><img
                                    src="/static/admin/assets/img/icons/pdf.svg" alt="img"></a>
                        </li>
                        <li>
                            <a data-bs-toggle="tooltip" data-bs-placement="top" title="excel"><img
                                    src="/static/admin/assets/img/icons/excel.svg" alt="img"></a>
                        </li>
                        <li>
                            <a data-bs-toggle="tooltip" data-bs-placement="top" title="print"><img
                                    src="/static/admin/assets/img/icons/printer.svg" alt="img"></a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="card" id="filter_inputs">
                <div class="card-body pb-0">
                    <div class="row">
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="form-group">
                                <input type="text" class="datetimepicker cal-icon"
                                       placeholder="Choose Date">
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="form-group">
                                <input type="text" placeholder="Enter Reference">
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="form-group">
                                <select class="select">
                                    <option>Choose Status</option>
                                    <option>Inprogress</option>
                                    <option>Complete</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-1 col-sm-6 col-12 ms-auto">
                            <div class="form-group">
                                <a class="btn btn-filters ms-auto"><img
                                        src="/static/admin/assets/img/icons/search-whites.svg" alt="img"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table datanew">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ngày</th>
                        <th>Sản phẩm</th>
                        <th>Màu</th>
                        <th>Số lượng</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="productReturn" items="${lstProductReturn}">
                        <tr>
                            <td>${productReturn.id}</td>
                            <td>${productReturn.returnDate}</td>
                            <td>${productReturn.nameProduct}</td>
                            <td>${productReturn.colorProduct}</td>
                            <td>${productReturn.quantity}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${productReturn.status == 0}">
                                        <span class="badges bg-lightred">In Progress</span>
                                    </c:when>
                                    <c:when test="${productReturn.status == 1}">
                                        <span class="badges bg-lightgreen">Completed</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badges bg-lightgrey">Unknown</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a class="me-3" href="edittransfer.html?id=${productReturn.id}">
                                    <img src="/static/admin/assets/img/icons/transfer1.svg" alt="img">
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

