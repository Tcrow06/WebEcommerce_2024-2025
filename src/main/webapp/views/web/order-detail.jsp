<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="<c:url value='/static/web/css/rating.css'/> ">

<script src="<c:url value='/static/web/js/rating.js'/> "></script>


<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
<!-- SweetAlert2 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<!-- SweetAlert2 JS -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style>
    /*.table-container {*/
    /*    margin: 20px auto;*/
    /*    max-width: 80%;*/
    /*}*/

    th, td {
        text-align: center;
    }

    .order-header span {
        display: block;
    }

    .order-status {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        padding: 15px;
        background-color: #f0f0f0;
        font-size: 14px;
        border-bottom: 1px solid #ddd;
    }

    .order-status div {
        flex: 1 1 30%;
        text-align: center;
        padding: 10px;
    }

    .order-steps {
        position: sticky;
        left: 0;
        width: 100vw;
        background-color: #fff;
        z-index: 1000;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        padding: 15px 20px;
    }

    .steps {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
        max-width: 2000px;
        margin: 0 auto;
    }

    .step {
        text-align: center;
        flex: 1 1 20%;
        max-width: 100px;
    }

    .step .step-icon-wrap {
        position: relative;
        width: 80px;
        height: 80px;
        margin: 0 auto;
    }

    .step .step-icon-wrap::before,
    .step .step-icon-wrap::after {
        content: '';
        position: absolute;
        top: 40%;
        width: 125%;
        height: 2px;
        background-color: #ddd;
    }

    .step .step-icon-wrap::before {
        left: -115%;
    }

    .step .step-icon-wrap::after {
        right: -115%;
    }

    .step:first-child .step-icon-wrap::before {
        display: none;
    }

    .step:last-child .step-icon-wrap::after {
        display: none;
    }

    .step-icon {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        background: #f0f0f0;
        line-height: 60px;
        text-align: center;
        font-size: 24px;
        border: 2px solid #ddd;
        margin: 0 auto;
    }

    .step.completed .step-icon {
        background: black;
        color: #fff;
        border-color: black;
    }

    .step-title {
        margin-top: 3px;
        font-size: 14px;
        color: #555;
        width: 120px;
        margin-left: -7px;
    }

    .footer-options {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 15px;
        background-color: #f5f5f5;
        border-top: 1px solid #ddd;
    }

    .footer-options label {
        font-size: 14px;
        color: #555;
    }

    .footer-options a {
        text-decoration: none;
        color: #0da9ef;
        font-weight: bold;
        font-size: 14px;
    }

    .footer-options a:hover {
        text-decoration: underline;
    }

    .order-steps p {
        text-align: center;
        font-size: 18px;
        font-weight: bold;
        color: #333;
        margin-bottom: 20px;
    }

    .table-container {
        /*max-width: 100%;*/
        /*overflow-x: auto;*/
        /*margin: 20px 0;*/

        max-width: 100%;
        overflow-x: auto;
        margin: 20px 0;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        background: #ffffff;
    }

    .table {
        width: 100%;
        border-collapse: collapse;
        border-radius: 8px;
        overflow: hidden;
    }

    .table thead th {
        background: linear-gradient(135deg, #6c757d, #343a40);
        color: #fff;
        padding: 10px 15px;
        text-align: center;
        font-size: 14px;
        text-transform: uppercase;
    }

    .table tbody tr {
        transition: background 0.3s;
    }

    .table tbody tr:hover {
        background: rgba(0, 0, 0, 0.05);
    }

    .table tbody td {
        padding: 12px 15px;
        text-align: center;
        color: #333;
        font-size: 14px;
        vertical-align: middle;
    }

    .table tbody td img.product-image {
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    }

    .table tbody tr:nth-child(odd) {
        background: #f9f9f9;
    }

    .table tbody tr:nth-child(even) {
        background: #ffffff;
    }

    .primary-btn {
        display: inline-block;
        padding: 8px 16px;
        background: linear-gradient(135deg, #333, #555); /* Chuyển từ đen nhạt sang xám đậm */
        color: #fff;
        border-radius: 4px;
        text-decoration: none;
        text-align: center;
        font-size: 14px;
        transition: background 0.3s ease;
    }

    .primary-btn:hover {
        background: linear-gradient(135deg, #555, #222); /* Chuyển từ xám đậm sang đen */
        text-decoration: none;
    }

    /* het nhap*/

    .table td, .table th {
        text-align: center;
        vertical-align: middle;
    }

    .table img.product-image {
        width: 100px;
        height: 100px;
        object-fit: cover;
        border-radius: 5px;
    }
    /* Responsive Styles */

    /* General responsive adjustments */
    @media (max-width: 3000px) {
        .order-steps {
            max-width: 100%;
            padding: 15px;
        }

        .steps {
            justify-content: space-around;
        }

        .step .step-icon-wrap::before,
        .step .step-icon-wrap::after {
            width: 200%;
        }

        .step .step-icon-wrap::before {
            left: -200%;
        }

        .step .step-icon-wrap::after {
            right: -200%;
        }
    }

    @media (max-width: 992px) {
        .steps {
            flex-wrap: wrap;
            justify-content: center;
        }

        .step {
            flex: 1 1 45%;
            max-width: 150px;
        }
    }

    @media (max-width: 768px) {
        .order-steps {
            padding: 10px;
        }

        .steps {
            flex-wrap: wrap;
            gap: 10px;
        }

        .step {
            flex: 1 1 100%;
            max-width: none;
            margin-bottom: 10px;
        }

        .step .step-icon-wrap::before,
        .step .step-icon-wrap::after {
            display: none;
        }

        .step-title {
            font-size: 12px;
        }
    }

    @media (max-width: 480px) {
        .order-status {
            flex-direction: column;
        }

        .order-status div {
            flex: 1 1 100%;
            margin-bottom: 5px;
        }

        .step .step-icon {
            width: 50px;
            height: 50px;
            line-height: 50px;
            font-size: 20px;
        }

        .step-title {
            font-size: 10px;
        }
    }
</style>

<section>
    <div class="order-tracking">
        <div class="order-steps">
            <div class="steps">
                <div class="step ${status == 'PENDING' || status == 'DELIVERED' || status == 'WAITING' || status == 'PROCESSED' || status == 'RECEIVED' || status == 'CANCELLED' ? 'completed' : ''}">
                    <div class="step-icon-wrap">
                        <div class="step-icon"
                             style="${status == 'PENDING' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="pe-7s-clock"></i>
                        </div>
                    </div>
                    <div class="step-title">Chờ xác nhận</div>
                </div>

                <div class="step ${status == 'DELIVERED' || status == 'WAITING' || status == 'PROCESSED' || status == 'RECEIVED' || status == 'CANCELLED' ? 'completed' : ''}">
                    <div class="step-icon-wrap">
                        <div class="step-icon"
                             style="${status == 'DELIVERED' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="pe-7s-car"></i>
                        </div>
                    </div>
                    <div class="step-title">Đang vận chuyển</div>
                </div>

                <div class="step ${status == 'DELIVERED' || status == 'WAITING' || status == 'PROCESSED' || status == 'RECEIVED' || status == 'CANCELLED' ? 'completed' : ''}">
                    <div class="step-icon-wrap">
                        <div class="step-icon"
                             style="${status == 'WAITING' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="pe-7s-box2"></i>
                        </div>
                    </div>
                    <div class="step-title">Đã vận chuyển</div>
                </div>

                <div class="step ${status == 'WAITING' || status == 'PROCESSED' || status == 'RECEIVED' || status == 'CANCELLED' ? 'completed' : ''}">
                    <div class="step-icon-wrap">
                        <div class="step-icon"
                             style="${status == 'RECEIVED' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="pe-7s-refresh-2"></i>
                        </div>
                    </div>
                    <div class="step-title">Trả hàng(Nếu có)</div>
                </div>

                <div class="step ${status == 'RECEIVED' ? 'completed' : ''}">
                    <div class="step-icon-wrap">
                        <div class="step-icon"
                             style="${status == 'RECEIVED' || status == 'CANCELLED' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="${status == 'CANCELLED' ? 'pe-7s-close-circle' : 'pe-7s-home'}"></i>
                        </div>
                    </div>
                    <div class="step-title">${status == 'CANCELLED' ? 'Đã hủy' : 'Đã nhận'}</div>
                </div>


<%--                <div class="step ${status == 'RECEIVED' ? 'completed' : ''}">--%>
<%--                    <div class="step-icon-wrap">--%>
<%--                        <div class="step-icon"--%>
<%--                             style="${status == 'RECEIVED' ? 'background-color: black; color: #fff;' : ''}">--%>
<%--                            <i class="pe-7s-home"></i>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="step-title">Đã nhận</div>--%>
<%--                </div>--%>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="table-container">
        <form id="return-form" action="/trang-chu/don-hang/danh-sach-don-hang/tra-san-pham" method="POST" onsubmit="return validateSelection()">
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                <tr>
                    <th><input type="checkbox" id="select-all"></th>
                    <th>Hình ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng </th>
                    <th>Giá</th>
                    <th>Màu</th>
                    <th>Size</th>
                    <c:if test="${status == 'RECEIVED'}">
                        <th>Đánh giá</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>

                <c:if test="${empty orderItemList}">
                    <p style="text-align: center">No items found.</p>
                </c:if>

                <c:if test="${not empty orderItemList}">

                    <c:set var="total" value="0" scope="page" />

                    <c:forEach var="item" items="${orderItemList}" varStatus="note">

                        <c:if test="${note.index == 0}">
                            <input type="hidden" id="firstIdOrderDetail" name="firstIdOrderDetail" value="${item.id}">
                        </c:if>

                        <tr>
                            <td><input type="checkbox" class="item-checkbox" name="selectedItems" value="${item.id}"></td>
                            <td><img src="<c:url value='/api-image?path=${item.imageUrl}'/>" alt="Product Image" class="product-image"></td>
                            <td>${item.productName}</td>

                            <c:if test="${status == 'DELIVERED'}">
                                <td>
                                    <button type="button" onclick="decreaseQuantity(${item.id})">-</button>
                                    <input type="number" id="quantity-${item.id}" name="quantities[${item.id}]" value="${item.quantity}" min="1" data-max="${item.quantity}" style="width: 50px; text-align: center;" readonly>
                                    <button type="button" onclick="increaseQuantity(${item.id})">+</button>
                                </td>
                            </c:if>
                            <c:if test="${status != 'DELIVERED'}">
                                  <td>${item.quantity}</td>
                            </c:if>
                            <td>${item.price}</td>
                            <td>${item.color}</td>
                            <td>${item.size}</td>

                            <c:set var="itemTotal" value="${item.quantity * item.price}"/>
                            <c:set var="total" value="${total + itemTotal}"/>

                            <c:if test="${status == 'RECEIVED'}">
                                <td>
                                <%--                                data-bs-toggle="modal" data-bs-target="#exampleModalCenter"--%>
                                        <button type="button" class="btn btn-dark btn-review" data-product-name="${item.productName}" data-product-image="${item.imageUrl}" data-orderdetail-id = "${item.id}">Đánh giá</button>
                                </td>
                            </c:if>
                        </tr>

                    </c:forEach>
                </c:if>
                </tbody>
            </table>

            <input type="hidden" name="totalAmount" value="${total}">

            <c:if test="${status == 'DELIVERED'}">
                <div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
                    <button type="submit" class="primary-btn">Trả hàng</button>
                </div><br>
            </c:if>
        </form>

<%--        cap nhat status hoan thanh--%>
        <c:if test="${status == 'PROCESSED'}">
            <div style="display: flex; justify-content: center; margin-top: 20px;">
                <button type="button" class="primary-btn confirm-order-btn">Xác nhận đơn hàng</button>
            </div>
        </c:if>

        <c:if test="${status == 'DELIVERED'}">
            <div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
                <button type="button" class="primary-btn confirm-order-btn">Xác nhận đơn hàng</button>
            </div>
        </c:if>

        <c:if test="${status == 'PENDING'}">
            <div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
                <button type="button" class="primary-btn cancle-order-btn">Hủy đơn hàng</button>
            </div>
        </c:if>

        <div style="display: flex; justify-content: center; margin-top: 20px;">
            <a href="javascript:void(0);" onclick="window.location.href='/trang-chu/don-hang';" class="primary-btn">Trở lại</a>
        </div>
    </div>
</section>


<div class="modal fade" id="exampleModalCenter" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Đánh giá sản phẩm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>

                    <input type="hidden" id="orderDetailId" class="order-detail-id" value="">

                    <div class="text-center">
                        <img id = "modal-product-image" src="https://images.unsplash.com/photo-1525171254930-643fc658b64e?q=80&w=1977&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                             class="profile-img rounded-circle img-thumbnail product-image" width="200px" height="200px" alt="Product Image">
                        <h2 id="modal-product-name" class="mt-3 text-dark">Tên sản phẩm</h2>
                        <p class="lead">Hãy để lại đánh giá cho sản phẩm của chúng tôi</p>

                        <!-- Star rating -->
                        <div id="stars" class="starrr mb-3"></div>
                        <div class="alert alert-success text-center d-none" role="alert">
                            You gave a rating of <span id="count">5</span> star(s)
                        </div>

                        <!-- Feedback textarea -->
                        <div class="mb-3">
                            <label for="feedback" id="dateFeedback" class="form-label">Đánh giá của bạn</label>
                            <textarea class="form-control" id="feedback" rows="3" placeholder="Điền nội dung ở đây"></textarea>
                        </div>
                    </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                            <button id="rating-button" type="button" class="btn btn-dark">Đánh giá</button>
                        </div>
                </form>
             </div>
        </div>
    </div>
</div>

<script>function increaseQuantity(itemId) {
    const elementId = "quantity-" + itemId;
    const quantityInput = document.getElementById(elementId);
    const currentQuantity = parseInt(quantityInput.value);
    const maxQuantity = parseInt(quantityInput.getAttribute('data-max'));

    if (currentQuantity < maxQuantity) {
        quantityInput.value = currentQuantity + 1;
    } else {
        Swal.fire({
            title: 'Lỗi!',
            text: 'Không thể tăng thêm số lượng, số lượng của sản phẩm đạt tối đa',
            icon: 'error',
            confirmButtonText: 'Đồng ý'
        });    }
}

function decreaseQuantity(itemId) {
    const elementId = "quantity-" + itemId;
    const quantityInput = document.getElementById(elementId);
    const currentQuantity = parseInt(quantityInput.value);
    const minQuantity = parseInt(quantityInput.getAttribute('min'));

    if (currentQuantity > minQuantity) {
        quantityInput.value = currentQuantity - 1;
    } else {
        Swal.fire({
            title: 'Lỗi!',
            text: 'Không thể giảm thêm số lượng, số lượng của sản phẩm đạt tối thiểu',
            icon: 'error',
            confirmButtonText: 'Đồng ý'
        });
    }
}

</script>

<script>
    document.getElementById('select-all').addEventListener('change', function () {
        const checkboxes = document.querySelectorAll('.item-checkbox');
        const isChecked = this.checked;
        checkboxes.forEach(checkbox => {
            checkbox.checked = isChecked;
        });
    });

    document.querySelectorAll('.confirm-order-btn').forEach(button => {
        button.addEventListener('click', function () {
            const form = document.getElementById('return-form');
            form.action = '/trang-chu/don-hang?actionType=CONFIRM'; // Đường dẫn xử lý
            form.method = 'POST';
            form.submit();
        });
    });

    document.querySelectorAll('.cancle-order-btn').forEach(button => {
        button.addEventListener('click', function () {
            const form = document.getElementById('return-form');
            form.action = '/trang-chu/don-hang?actionType=CANCEL'; // Đường dẫn xử lý
            form.method = 'POST';
            form.submit();
        });
    });
</script>

<script>

    function updateStar(value) {
        // Clear the existing stars
        $('#stars').empty();

        // Add 5 stars (empty by default)
        for (let i = 0; i < 5; i++) {
            // If the current index is less than the rating value, make the star filled
            if (i < value) {
                $('#stars').append("<span class='glyphicon glyphicon-star-empty text-warning'>★</span>");
            } else {
                $('#stars').append("<span class='glyphicon glyphicon-star-empty'>★</span>");
            }
        }
    }

    function updateReviewModal (response) {
        $('#feedback').text(response.content);
        $('#rating-button').hide()
        updateStar(response.numberOfStars)
        $('#dateFeedback').text("Bạn đã đánh giá sản phẩm này vào ngày " + response.dateString)

        $('#exampleModalCenter').modal('show');
    }



    $(document).ready(function () {

        // Lắng nghe sự kiện click cho tất cả các button có class 'btn-review'
        $(document).on('click', '.btn-review', function () {

            var productName = $(this).data('product-name');
            var productImage = $(this).data('product-image');
            var imageUrl = '${pageContext.request.contextPath}/api-image?path=' + productImage;

            $('#modal-product-name').text(productName)
            $('#modal-product-image').attr('src', imageUrl);

            var orderDetailId = $(this).data('orderdetail-id');
            $('#orderDetailId').val(orderDetailId)

            // Gửi AJAX tới API với các thông tin đã lấy từ button
            $.ajax({
                url: '/api-product-review',
                type: 'GET',
                data: {
                    orderDetailId: orderDetailId
                },
                dataType: 'json',
                success: function (response) {
                    if (response) {
                        updateReviewModal(response)
                    } else {
                        $('#stars').empty();
                        $('#stars').starrr()
                        $('#rating-button').show()
                        $('#dateFeedback').text("Bạn chưa đánh giá sản phẩm này !")
                    }

                    $('#exampleModalCenter').modal('show');
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi nếu có
                    alert("Lỗi xử lý: " + error);
                }
            });
        });

    });


</script>
<script>
        // Lắng nghe sự kiện click vào nút "Đánh giá"
        $('#rating-button').on('click', function () {
            // Lấy thông tin từ modal
            const orderDetailId = $('#orderDetailId').val();
            const feedback = $('#feedback').val();
            const stars = $('#stars span.text-warning').length; // Đếm số sao được chọn (sao có class "text-warning")

            if (!stars || !feedback.trim()) {
                alert("Vui lòng điền đầy đủ đánh giá và chọn số sao!");
                return;
            }

            // Gửi AJAX để gửi dữ liệu đánh giá lên server
            $.ajax({
                url: '/api-product-review', // Đường dẫn API xử lý review
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    customerId: ${customerId},
                    content: feedback,
                    orderDetail: {
                        id: orderDetailId
                    },
                    numberOfStars: stars
                }),
                success: function (response) {
                    if (response == "Thành công !") {
                        alert("Đánh giá của bạn đã được gửi thành công!");
                        $('#exampleModalCenter').modal('hide'); // Ẩn modal sau khi đánh giá thành công
                    } else {
                        alert("Có lỗi xảy ra khi gửi đánh giá. Vui lòng thử lại.");
                    }
                },
                error: function (xhr, status, error) {
                    alert("Lỗi xử lý: " + error);
                }
            });
        });
</script>

<script>
    function validateSelection() {
        const checkboxes = document.querySelectorAll('.item-checkbox:checked');

        if (checkboxes.length === 0) {
            Swal.fire({
                title: 'Lỗi!',
                text: 'Bạn phải chọn sản phẩm để trả hàng!',
                icon: 'error',
                confirmButtonText: 'Đồng ý'
            });
            return false;
        }

        return true;
    }
</script>
