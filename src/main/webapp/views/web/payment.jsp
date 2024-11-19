<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
    .checkout__input > input{
        color: #0b0b0b;
        font-weight: bold;
    }
    .change__address{
        font-weight: normal;
        color: silver;
        text-transform: lowercase;
    }
    .note{
        font-weight: normal;
        color: silver;
    }

    .checkout__info {
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .order__title {
        font-size: 20px;
        color: #333;
    }

    hr {
        border-top: 1px solid #ddd;
        margin: 5px 0 15px;
    }

</style>

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__text">
                    <h4>Check Out</h4>
                    <div class="breadcrumb__links">
                        <a href="<c:url value="/trang-chu"/>">Home</a>
                        <a href="<c:url value="/san-pham"/>">Shop</a>
                        <span>Check Out</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">
    <div class="container">
        <div class="checkout__form">
            <form action="#">
                <div class="row">
                    <!-- Phần thông tin nhận hàng -->
                    <div class="col-lg-10 col-md-6 mx-auto">
                        <div class="checkout__info d-flex justify-content-between align-items-center p-4 mb-4 rounded shadow-sm" style="background-color: #f9f9f9; border: 1px solid #ddd;">
                            <!-- Phần thông tin bên trái -->
                            <div>
                                <h4 class="order__title" style="font-weight: 600; color: #333;">Nguyễn Công Quý</h4>
                                <hr style="margin: 10px 0; border-top: 3px solid #333;" />
                                <p class="mb-2"><strong>Số điện thoại:</strong> <span>0976870127</span></p>
                                <p class="mb-0"><strong>Địa chỉ:</strong> <span>Địa chỉ ở đây</span></p>
                            </div>

                            <!-- Nút thay đổi địa chỉ -->
                            <a href="javascript:void(0);"
                               class="btn d-flex align-items-center"
                               onfocus="this.blur()"
                               data-bs-toggle="modal"
                               data-bs-target="#form2Modal"
                               style="background-color: black; color: white; text-decoration: none; padding: 10px 15px; border: 1px solid black;">
                                Thay đổi địa chỉ của bạn
                            </a>
                        </div>
                    </div>

                    <%--Phần thông tind đơn hàng--%>
                    <div class="col-lg-10 col-md-6 mx-auto">
                        <div class="checkout__order">
                            <h4 class="order__title">Đơn hàng của bạn</h4>
                            <div class="checkout__order__products">
                                Sản phẩm <span>Tổng tiền</span>
                            </div>
                            <ul class="checkout__total__products">
                                <c:forEach items="${orderDTO.orderDetails}" var="order">
                                    <li>- ${order.productVariant.name} ${order.productVariant.size} ${order.productVariant.color} x${order.quantity}<span><fmt:formatNumber type = "number" maxFractionDigits = "3" value="${order.total}" /> VND</span></li>
                                    <li style="display: flex; justify-content: left; align-items: center; gap: 10px; font-weight: bold; margin: -15px 0 20px 10px">

                                        <c:if test="${not empty order.productDiscount}">
                                            <span>${order.productVariant.price * (100 - order.productDiscount.discountPercentage) / 100}</span>
                                            &nbsp;&nbsp;
                                        </c:if>
                                        <span style="text-decoration: ${not empty order.productDiscount ? 'line-through' : 'none'};">
                                                ${order.productVariant.price}
                                        </span>
                                    </li>
                                </c:forEach>
                            </ul>
                            <ul class="checkout__total__all">
                                <li>Tổng tiền <span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${orderDTO.total}" /> VND</span></li>
                                <c:if test="${not empty orderDTO.billDiscount}">
                                    <c:set var="percent" value="${orderDTO.billDiscount.discountPercentage}"/>
                                    <li>Số tiền được giảm (${percent}% tối đa: <fmt:formatNumber type = "number" maxFractionDigits = "3" value="${orderDTO.billDiscount.maximumAmount}"/> VND)<span><fmt:formatNumber type = "number" maxFractionDigits = "3" value="${orderDTO.maximumDiscount}" /> VND</span></li>

                                    <li>Tổng thanh toán <span><fmt:formatNumber type = "number" maxFractionDigits = "3" value="${orderDTO.total-orderDTO.maximumDiscount}" /> VND</span></li>
                                </c:if>
                                <c:if test="${empty orderDTO.billDiscount}">
                                    <li>Số tiền được giảm <span>0 VND</span></li>
                                    <li>Tổng thanh toán <span><fmt:formatNumber type = "number" maxFractionDigits = "3" value="${orderDTO.total}"/> VND</span></li>
                                </c:if>
                            </ul>
                            <div class="note" style="font-size: 14px; color :black">*<span style="color: #E53637">Phí vận chuyển</span>: Khu vưc Tp.Hồ Chí Minh: 15k, ngoài khu vực Tp.Hồ Chí Minh: 30k </div>

                            <div class="form-check">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="payment"
                                        value="cash"
                                />
                                <label class="form-check-label">
                                    Thanh toán bằng tiền mặt
                                </label>
                            </div>
                            <br />
                            <div class="form-check">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="payment"
                                        checked
                                        value="bank"
                                />
                                <label class="form-check-label">
                                    Thanh toán bằng chuyển khoản
                                </label>
                            </div>
                            <br />

                            <a href="javascript:void(0);" id="placed-order" class="site-btn d-flex justify-content-center align-items-center">Đặt hàng</a>

                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<!-- Checkout Section End -->

<%--Mở form thay đổi địa chỉ--%>
<div class="modal fade" id="form2Modal" tabindex="-1" aria-labelledby="form2ModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content shadow">
            <div class="modal-header">
                <h4 class="modal-title" id="form2ModalLabel">Chọn địa chỉ</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" style="max-height: 400px; overflow-y: auto;">

                <!-- Danh sách địa chỉ của người dùng -->
                <c:forEach items="${orderInfos}" var="orderInfo">
                    <div class="card mb-3">
                        <div class="card-body">
                            <%--Giấu thẻ này để tí có cái mà xóa =))--%>
                            <input type="hidden" id="orderId" value="${orderInfo.id}">

                            <h5 style="margin-top: 10px">
                                ${orderInfo.recipient}
                                <c:if test="${orderInfo.isDefault == 1}">
                                    <span class="badge bg-primary-color ms-2">Mặc định</span>
                                </c:if>
                            </h5>
                            <p><strong>Điện thoại: </strong>${orderInfo.phone}</p>
                            <p><strong>Địa chỉ:</strong>
                                    ${orderInfo.address.concrete}, ${orderInfo.address.commune},
                                    ${orderInfo.address.district}, ${orderInfo.address.city}
                            </p>
                            <div class="row">
                                <div class="col-4">
                                    <button type="button" class="btn btn-link text-decoration-none"
                                            onclick="">
                                        Chọn địa chỉ
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>


                <!-- Nút thêm địa chỉ mới -->
                <div class="d-flex justify-content-center mt-3">
                    <button type="button"
                        onfocus="this.blur()"
                        class="btn d-flex align-items-center"
                        onclick="toggleNewAddressForm()"
                        style="background-color: black; color: white; text-decoration: none; padding: 10px 15px; border: 1px solid black;">Thêm địa chỉ mới
                    </button>
                </div>

                <!-- Form thêm địa chỉ mới, mặc định ẩn đi -->
                <div id="newAddressForm" class="mt-4" style="display: none;">
                    <h5 style="font-weight: bold;">Thêm địa chỉ mới</h5>
                    <hr />
                    <form>

                        <div class="mb-3">
                            <label for="recipientName" class="form-label" style="font-weight: bold;">Tên người nhận</label>
                            <input type="text" class="form-control" id="recipientName" required>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label" style="font-weight: bold;">Điện thoại</label>
                            <input type="text" class="form-control" id="phone" required>
                        </div>

                        <div class="mb-3">
                            <label for="city" class="form-label" style="font-weight: bold;">Tỉnh/Thành phố</label>
                            <select class="form-control" id="city" required>
                                <option value="" selected>Chọn tỉnh thành</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="district" class="form-label" style="font-weight: bold;">Quận/Huyện</label>
                            <select class="form-control" id="district" required>
                                <option value="" selected>Chọn quận huyện</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="ward" class="form-label" style="font-weight: bold;">Xã/Phường/Thị
                                trấn</label>
                            <select class="form-control" id="ward" required>
                                <option value="" selected>Chọn phường xã</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="specificAddress" class="form-label" style="font-weight: bold;">Địa chỉ cụ
                                thể</label>
                            <input type="text" class="form-control" id="specificAddress" required>
                        </div>

                        <!-- Nút lưu và quay về -->
                        <div class="d-flex justify-content-end">
                            <button type="button" class="btn btn-secondary me-2"
                                    onclick="toggleNewAddressForm()">Quay về</button>
                            <button type="button" class="btn btn-success" onclick="saveOrderInfo()">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



<script
        src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"/>"
        referrerpolicy="no-referrer"
></script>
<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"/>"></script>
<script>

    // Gọi API đầu tiên
    $(document).ready(() => {
        // Bộ API
        const host = "https://provinces.open-api.vn/api/";

        var callAPI = (api) => {
            return axios.get(api)
                .then((response) => {
                    renderData(response.data, "city");
                })
                .catch((error) => {
                    console.error("Error fetching city data:", error);
                });
        }

        callAPI(host + '?depth=1');

        var callApiDistrict = (api) => {
            return axios.get(api)
                .then((response) => {
                    renderData(response.data.districts, "district");
                })
                .catch((error) => {
                    console.error("Error fetching district data:", error);
                });
        }

        var callApiWard = (api) => {
            return axios.get(api)
                .then((response) => {
                    renderData(response.data.wards, "ward");
                })
                .catch((error) => {
                    console.error("Error fetching ward data:", error);
                });
        }


        var renderData = (array, select) => {
            let selectElement = document.querySelector("#" + select);
            selectElement.innerHTML = "";

            let defaultOption = document.createElement("option");
            defaultOption.text = "Chọn";
            defaultOption.value = "";
            defaultOption.disabled = true;
            defaultOption.selected = true;
            selectElement.appendChild(defaultOption);

            array.forEach(element => {
                let option = document.createElement("option");
                option.text = element.name;
                option.value = element.name;
                option.setAttribute("data-id", element.code);
                selectElement.appendChild(option);
            });
        };


        $("#city").change(() => {
            const cityId = $("#city").find(':selected').data('id');
            if (cityId) {
                callApiDistrict(host + "p/" + cityId + "?depth=2");
            }
        });

        $("#district").change(() => {
            const districtId = $("#district").find(':selected').data('id');
            if (districtId) {
                callApiWard(host + "d/" + districtId + "?depth=2");
            }
        });
    });

    var currentUrl = window.location.href;

    // Kiểm tra xem URL có tham số 'state' hay không
    var stateMatch = currentUrl.match(/[?&]order=([^&]+)/);
    if (stateMatch) {
        // Lấy giá trị của tham số 'state'
        var stateValue = stateMatch[1];

        // Lưu giá trị 'state' vào sessionStorage
        sessionStorage.setItem('order', stateValue);

        // Xóa tham số 'state' khỏi URL hiển thị trên trình duyệt
        var newUrl = currentUrl.replace(/([?&])order=[^&]+/, '$1').replace(/&$/, '').replace(/\?$/, '');
        window.history.replaceState({}, document.title, newUrl);
    }


    $('#placed-order').click(function (event) {

        let isValid= true;
        const inputs = document.querySelectorAll('.checkout__input input');
        inputs.forEach(input => {
            const feedback = input.nextElementSibling; // Tìm thẻ <small> gần nhất
            if (input.value.trim() === '') {
                feedback.textContent = 'Vui lòng nhập thông tin này!';
                feedback.style.color = 'red';
                isValid = false;
            } else {
                if(feedback){
                    feedback.textContent = '';
                }
            }
        });


        if (isValid){
            let order = JSON.parse('${orderDTOJson}');
            let recipient= document.getElementById("recipient").value;
            let phone= document.getElementById("phone").value;
            let address= {
                city: document.getElementById("city").value,
                district: document.getElementById("district").value,
                commune: document.getElementById("commune").value,
                concrete: document.getElementById("concrete").value
            }
            const paymentMethod = document.querySelector('input[name="payment"]:checked').value;

            delete order.orderInfoDTO;
            order.orderInfoDTO = {
                recipient: recipient,
                phone: phone,
                address: address,
            };
            order.paymentMethod =paymentMethod


            event.preventDefault();
            $.ajax({
                type: "POST",
                url: "/thanh-toan",
                contentType: "application/json",
                data: JSON.stringify(order),
                success: function(response) {
                    if(response.status ==="success"){
                        alert(response.message);
                        window.location.href = response.redirectUrl.toString() ;

                    }else if(response.status ==="error"){
                        alert(response.message);
                        window.location.href = response.redirectUrl.toString() ;

                    }else if( response.status==="warning"){
                        alert(response.message);
                        window.location.reload();
                    }
                },
                error: function(xhr, status, error) {
                    window.location.href = response.redirectUrl.toString() ;
                    // Xử lý khi có lỗi
                    console.error("Lỗi: ", error);
                    alert("Có lỗi xảy ra, vui lòng thử lại.");
                }
            });
        }


    });

    // Hàm gọi model thêm địa chỉ
    function toggleNewAddressForm() {
        const form = document.getElementById('newAddressForm');
        form.style.display = form.style.display === 'none' ? 'block' : 'none';
    }

</script>