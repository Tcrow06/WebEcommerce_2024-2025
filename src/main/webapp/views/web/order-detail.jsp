<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
<style>
    .table-container {
        margin: 20px auto;
        max-width: 80%;
    }

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
        max-width: 100%;
        overflow-x: auto;
        margin: 20px 0;
    }

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
                <div class="step ${status == 'PENDING' || status == 'DELIVERED' || status == 'WAITING' || status == 'PROCESSED' || status == 'RECEIVED' ? 'completed' : ''}">
                    <div class="step-icon-wrap">
                        <div class="step-icon"
                             style="${status == 'PENDING' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="pe-7s-clock"></i>
                        </div>
                    </div>
                    <div class="step-title">Chờ xác nhận</div>
                </div>

                <div class="step ${status == 'DELIVERED' || status == 'WAITING' || status == 'PROCESSED' || status == 'RECEIVED' ? 'completed' : ''}">
                    <div class="step-icon-wrap">
                        <div class="step-icon"
                             style="${status == 'DELIVERED' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="pe-7s-car"></i>
                        </div>
                    </div>
                    <div class="step-title">Đang vận chuyển</div>
                </div>

                <div class="step ${status == 'DELIVERED' || status == 'WAITING' || status == 'PROCESSED' || status == 'RECEIVED' ? 'completed' : ''}">
                    <div class="step-icon-wrap">
                        <div class="step-icon"
                             style="${status == 'WAITING' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="pe-7s-box2"></i>
                        </div>
                    </div>
                    <div class="step-title">Đã vận chuyển</div>
                </div>

                <div class="step ${status == 'WAITING' || status == 'PROCESSED' || status == 'RECEIVED' ? 'completed' : ''}">
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
                             style="${status == 'RECEIVED' ? 'background-color: black; color: #fff;' : ''}">
                            <i class="pe-7s-home"></i>
                        </div>
                    </div>
                    <div class="step-title">Đã nhận</div>
                </div>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="table-container">
        <form id="return-form" action="/trang-chu/don-hang/danh-sach-don-hang/tra-san-pham" method="POST">
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                <tr>
                    <th><input type="checkbox" id="select-all"></th>
                    <th>Image</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Color</th>
                    <th>Size</th>
                </tr>
                </thead>
                <tbody>

                <c:if test="${empty orderItemList}">
                    <p style="text-align: center">No items found.</p>
                </c:if>

                <c:if test="${not empty orderItemList}">
                    <c:forEach var="item" items="${orderItemList}" varStatus="note">

                        <c:if test="${note.index == 0}">
                            <input type="hidden" id="firstIdOrderDetail" name="firstIdOrderDetail" value="${item.id}">
                        </c:if>

                        <tr>
                            <td><input type="checkbox" class="row-checkbox" name="selectedItems" value="${item.id}"></td>
                            <td><img src="${item.imageUrl}" alt="Product Image" class="product-image"></td>
                            <td>${item.productName}</td>
                            <td>${item.quantity}</td>
                            <td>${item.price}</td>
                            <td>${item.color}</td>
                            <td>${item.size}</td>
                        </tr>

                    </c:forEach>
                </c:if>
                </tbody>
            </table>

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

        <div style="display: flex; justify-content: center; margin-top: 20px;">
            <a href="javascript:void(0);" onclick="window.location.href='/trang-chu/don-hang';" class="primary-btn">Trở lại</a>
        </div>
    </div>
</section>


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
            form.action = '/trang-chu/don-hang'; // Đường dẫn xử lý
            form.method = 'POST';
            form.submit();
        });
    });
</script>
