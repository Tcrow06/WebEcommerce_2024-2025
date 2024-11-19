<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Create Purchase Return</h4>
            <h6>Add/Update Purchase Return</h6>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Mã đơn</th>
                            <th>Ngày khởi tạo</th>
                            <th>Tổng giá trị</th>
                            <th>Số lượng sản phẩm</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td>ELV${order.id}</td>
                                    <td>${order.dateTime}</td>
                                    <td>${order.totalOrder}</td>
                                    <td>${order.allQuantity}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


