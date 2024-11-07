<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Product Edit Category</h4>
            <h6>Edit a product Category</h6>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <form id="categoryForm">
                <div class="row">
                    <div class="col-lg-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label>Category Name</label>
                            <input type="text" name="name" value="">
                        </div>
                    </div>
                    <div class="col-lg-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label>Category Code</label>
                            <input type="text" name="code" value="">
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <a href="javascript:void(0);" class="btn btn-submit me-2" id="submitBtn">Submit</a>
                        <a href="categorylist.html" class="btn btn-cancel">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="table-top">
                    <div class="search-set">
                        <div class="search-path">
                            <a class="btn btn-filter" id="filter_search">
                                <img src="<c:url value='/static/admin/assets/img/icons/filter.svg'/> " alt="img">
                                <span><img src="assets/img/icons/closes.svg" alt="img"></span>
                            </a>
                        </div>
                        <div class="search-input">
                            <a class="btn btn-searchset"><img src="<c:url value='/static/admin/assets/img/icons/search-white.svg'/> " alt="img"></a>
                        </div>
                    </div>
                </div>

                <div class="card" id="filter_inputs">
                    <div class="card-body pb-0">
                        <div class="row">
                            <div class="col-lg-2 col-sm-6 col-12">
                                <div class="form-group">
                                    <select class="select">
                                        <option>Choose Category</option>
                                        <option>Computers</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-2 col-sm-6 col-12">
                                <div class="form-group">
                                    <select class="select">
                                        <option>Choose Sub Category</option>
                                        <option>Fruits</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-2 col-sm-6 col-12">
                                <div class="form-group">
                                    <select class="select">
                                        <option>Choose Sub Brand</option>
                                        <option>Iphone</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-1 col-sm-6 col-12 ms-auto">
                                <div class="form-group">
                                    <a class="btn btn-filters ms-auto"><img src="assets/img/icons/search-whites.svg" alt="img"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="table  datanew">
                        <thead>
                        <tr>
                            <th>Category name</th>
                            <th>Category Code</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${model}">
                            <tr>
                                <td class="productimgname">
                                    <a href="javascript:void(0);">${item.name}</a>
                                </td>
                                <td>${item.code}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#submitBtn').click(function() {
                // Lấy dữ liệu từ form và chuyển thành đối tượng
                var formData = $("#categoryForm").serializeArray();  // Lấy dữ liệu dưới dạng mảng đối tượng

                // Tạo đối tượng JSON từ dữ liệu form
                var data = {};
                $.each(formData, function(i, field) {
                    data[field.name] = field.value;
                });

                // Gửi yêu cầu POST đến server
                $.ajax({
                    url: '/api-category',
                    type: 'POST',
                    contentType: 'application/json',  // Đảm bảo gửi dữ liệu dưới dạng JSON
                    data: JSON.stringify(data),  // Chuyển đối tượng thành JSON
                    dataType: 'json',
                    success: function(response, textStatus, xhr) {
                        if (xhr.status === 200) {
                            alert(response); // Thông báo thành công
                            // Reload bảng category nếu thành công
                            var tableBody = $(".datanew tbody");

                            var row = $("<tr>");
                            row.append("<td class='productimgname'><a href='javascript:void(0);'>" + data.name + "</a></td>");
                            row.append("<td>" + data.code + "</td>");
                            tableBody.append(row);
                        }
                    },
                    error: function(xhr, status, error) {
                        alert('Failed to add category: ' + error);
                    }
                });
            });
        });
    </script>

</div>