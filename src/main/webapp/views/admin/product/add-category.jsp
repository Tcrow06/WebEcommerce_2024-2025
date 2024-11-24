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
                            <label>Tên loại sản phẩm</label>
                            <input type="text" name="name" value="" placeholder="Nhập tên loại sản phẩm ở đây">
                        </div>
                    </div>
                    <div class="col-lg-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label>Category Code</label>
                            <input type="text" name="code" value="" placeholder="Nhập code ở đây">
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
                        <div class="search-input">
                            <a class="btn btn-searchset"><img src="<c:url value='/static/admin/assets/img/icons/search-white.svg'/> " alt="img"></a>
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

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                    <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn thực hiện ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal" id="cancelButton">Hủy</button>
                    <button type="button" class="btn btn-primary" id="okButton">OK</button>
                </div>
            </div>
        </div>
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>

        function showConfirmationModal() {
            return new Promise((resolve) => {
                // Hiển thị modal
                $('#exampleModal').modal('show');

                // Khi người dùng nhấn "OK"
                $('#okButton').one('click', function () {
                    resolve(true); // Người dùng đồng ý
                    $('#exampleModal').modal('hide');
                });

                // Khi người dùng nhấn "Cancel"
                $('#cancelButton').one('click', function () {
                    resolve(false); // Người dùng hủy
                    $('#exampleModal').modal('hide');
                });
            });
        }

        $(document).ready(function() {
            $('#submitBtn').click(function() {

                showConfirmationModal().then((result) => {
                    if (!result) {
                        console.log("User cancelled the action.");
                        return; // Người dùng chọn "Cancel", dừng xử lý
                    }
                    // Lấy dữ liệu từ form và chuyển thành đối tượng
                    var formData = $("#categoryForm").serializeArray();  // Lấy dữ liệu dưới dạng mảng đối tượng

                    // Tạo đối tượng JSON từ dữ liệu form
                    var data = {};
                    $.each(formData, function (i, field) {
                        data[field.name] = field.value;
                    });

                    // Gửi yêu cầu POST đến server
                    $.ajax({
                        url: '/api-category',
                        type: 'POST',
                        contentType: 'application/json',  // Đảm bảo gửi dữ liệu dưới dạng JSON
                        data: JSON.stringify(data),  // Chuyển đối tượng thành JSON
                        dataType: 'json',
                        beforeSend: function () {
                            // Hiển thị loader trước khi AJAX bắt đầu
                            $('#global-loader').css('display', 'flex');
                        },
                        success: function (response, textStatus, xhr) {
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
                        error: function (xhr, status, error) {
                            if (xhr.status === 409) {
                                alert("Lỗi: " + xhr.responseText); // Hiển thị thông báo lỗi khi mã code đã tồn tại
                            } else if (xhr.status === 400) {
                                alert("Lỗi: Dữ liệu không hợp lệ.");
                            } else {
                                alert("Đã xảy ra lỗi: " + error);
                            }
                        },
                        complete: function () {
                            $('#global-loader').css('display', 'none');
                        }
                    });
                });
            });
        });
    </script>

</div>