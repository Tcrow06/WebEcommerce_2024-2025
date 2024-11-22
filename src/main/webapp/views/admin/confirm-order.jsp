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
                            <th>Xác nhận</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orders}">
                            <c:if test="${order.status != 'PENDING'}">

                            </c:if>
                            <c:if test="${order.status == 'PENDING'}">
                                <tr>
                                    <td>ELV${order.id}</td>
                                    <td>${order.dateTime}</td>
                                    <td>${order.totalOrder}</td>
                                    <td>${order.allQuantity}</td>
                                    <td>
                                        <a href="javascript:void(0);" class="submitBtn" data-order-id="${order.id}">
                                            <i class="fas fa-check" style="font-size: 24px; color: black;"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    // document.querySelectorAll('.submitBtn').forEach(button => {
    //     button.addEventListener('click', function () {
    //         const orderId = this.getAttribute('data-order-id');
    //         const confirmSubmit = confirm("Bạn có muốn submit không?");
    //         if (confirmSubmit) {
    //             const form = document.createElement('form');
    //             form.method = 'POST';
    //             form.action = '/chu-cua-hang/xac-nhan-don-hang';
    //
    //             const input = document.createElement('input');
    //             input.type = 'hidden';
    //             input.name = 'orderId';
    //             input.value = orderId;
    //             form.appendChild(input);
    //
    //             document.body.appendChild(form);
    //             form.submit();
    //         }
    //     });
    // });

    document.querySelectorAll('.submitBtn').forEach(button => {
        button.addEventListener('click', function () {
            const orderId = this.getAttribute('data-order-id');

            // Sử dụng SweetAlert thay vì confirm()
            Swal.fire({
                text: "Bạn muốn xác nhận đơn hàng này?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Xác nhận',
                cancelButtonText: 'Hủy'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Nếu người dùng nhấn Xác nhận
                    const form = document.createElement('form');
                    form.method = 'POST';
                    form.action = '/chu-cua-hang/xac-nhan-don-hang';

                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'orderId';
                    input.value = orderId;
                    form.appendChild(input);

                    document.body.appendChild(form);
                    form.submit();

                    Swal.fire({
                        title: 'Thành công!',
                        text: 'Đơn hàng của bạn đã được xác nhận.',
                        icon: 'success',
                        confirmButtonText: 'OK'
                    });
                }
            });
        });
    });

</script>

