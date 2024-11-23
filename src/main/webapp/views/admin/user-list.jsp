<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Danh sách khách hàng</h4>
            <h6>Dùng để quản lý khách hàng của bạn</h6>
        </div>
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
                                <input type="text" placeholder="Enter User Name">
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="form-group">
                                <input type="text" placeholder="Enter Phone">
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="form-group">
                                <input type="text" placeholder="Enter Email">
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 col-12">
                            <div class="form-group">
                                <select class="select">
                                    <option>Disable</option>
                                    <option>Enable</option>
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
                <table class="table  datanew">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Họ tên</th>
                        <th>Điện thoại</th>
                        <th>Điểm tích lũy</th>
                        <th>Số lần hủy đơn</th>
                        <th>Trạng thái</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Nguyễn Đức Sang</td>
                        <td>0946576890</td>
                        <td>500</td>
                        <td>4</td>
                        <td>
                            <div
                                    class="status-toggle d-flex justify-content-between align-items-center">
                                <input type="checkbox" id="user18" class="check" checked="">
                                <label for="user18" class="checktoggle">checkbox</label>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>




