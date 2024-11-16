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
                    <div class="col-lg-8 col-md-6">
                        <div class="checkout__order">
                            <h4 class="order__title">Đơn hàng của bạn</h4>
                            <div class="checkout__order__products">
                                Sản phẩm <span>Tổng tiền</span>
                            </div>
                            <ul class="checkout__total__products">
                                <c:forEach items="${orderDTO.orderDetails}" var="order">
                                    <li>- ${order.productVariant.name} ${order.productVariant.size} ${order.productVariant.color} x${order.quantity}<span><fmt:formatNumber type = "number" maxFractionDigits = "3" value="${order.total}" /> VND</span></li>
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
                            <div class="form-check">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="payment"
                                />
                                <label class="form-check-label" for="flexRadioDefault1">
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
                                />
                                <label class="form-check-label" for="flexRadioDefault2">
                                    Thanh toán bằng chuyển khoản
                                </label>
                            </div>
                            <br />

                            <a href="javascript:void(0);" id="placed-order" class="site-btn d-flex justify-content-center align-items-center">Đặt hàng</a>

                        </div>
                    </div>
                    <c:if test="${not empty orderDTO.orderInfoDTO }">
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__title">
                                <h6 class="col-lg-8">Thông tin giao hàng</h6>
                                <div class="col-lg-8 change__address" >thay đổi địa chỉ</div>
                            </div>
                            <div class="checkout__input">
                                <p>Họ và tên<span>*</span></p>
                                <input class="" type="text" id="recipient" readonly value="${orderDTO.orderInfoDTO.recipient}"/>
                            </div>
                            <div class="checkout__input">
                                <p>Số điện thoại<span>*</span></p>
                                <input type="text" id="phone" readonly value="${orderDTO.orderInfoDTO.phone}" />
                            </div>
                            <div class="checkout__input">
                                <p>Tỉnh/Thành phố<span>*</span></p>
                                <input type="text" id="city" readonly value="${orderDTO.orderInfoDTO.address.city}" />
                            </div>
                            <div class="checkout__input">
                                <p>Quận/Huyện<span>*</span></p>
                                <input type="text" id="district" readonly value="${orderDTO.orderInfoDTO.address.district}" />
                            </div>
                            <div class="checkout__input">
                                <p>Phường/Xã<span>*</span></p>
                                <input type="text" id="commune" readonly value="${orderDTO.orderInfoDTO.address.commune}" />
                            </div>
                            <div class="checkout__input">
                                <p>Số nhà, tên đường<span>*</span></p>
                                <input type="text" id="concrete" readonly value="${orderDTO.orderInfoDTO.address.concrete}"/>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${empty orderDTO.orderInfoDTO }">
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__title">
                                <h6 class="col-lg-8">Thông tin giao hàng</h6>
                                <div class="col-lg-8 change__address" >thay đổi địa chỉ</div>
                            </div>
                            <div class="checkout__input">
                                <p>Họ và tên<span>*</span></p>
                                <input class="" type="text" id="recipient" value="" placeholder="Nhập họ và tên"/>
                                <small class="feedback error-message"></small>
                            </div>
                            <div class="checkout__input">
                                <p>Số điện thoại<span>*</span></p>
                                <input type="text" id="phone" value="" placeholder="Nhập số điện thoại"/>
                                <small class="feedback error-message"></small>
                            </div>
                            <div class="checkout__input">
                                <p>Tỉnh/Thành phố<span>*</span></p>
                                <input type="text" id="city" value="" placeholder="Nhập tỉnh/thành phố" />
                                <small class="feedback error-message"></small>
                            </div>
                            <div class="checkout__input">
                                <p>Quận/Huyện<span>*</span></p>
                                <input type="text" id="district"  value="" placeholder="Nhập quận/huyện"/>
                                <small class="feedback error-message"></small>
                            </div>
                            <div class="checkout__input">
                                <p>Phường/Xã<span>*</span></p>
                                <input type="text" id="commune"  value="" placeholder="Nhập phường xã"/>
                                <small class="feedback error-message"></small>
                            </div>
                            <div class="checkout__input">
                                <p>Số nhà, tên đường<span>*</span></p>
                                <input type="text" id="concrete" value="" placeholder="Nhập số nhà, tên đường"/>
                                <small class="feedback error-message"></small>
                            </div>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</section>
<!-- Checkout Section End -->


<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
        referrerpolicy="no-referrer"
></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script>
    $(document).ready(function () {
    const host = "https://provinces.open-api.vn/api/";
    var callAPI = (api) => {
        return axios.get(api).then((response) => {
            renderData(response.data, "city");
        });
    };
    callAPI("https://provinces.open-api.vn/api/?depth=1");
    var callApiDistrict = (api) => {
        return axios.get(api).then((response) => {
            renderData(response.data.districts, "district");
        });
    };
    var callApiWard = (api) => {
        return axios.get(api).then((response) => {
            renderData(response.data.wards, "ward");
        });
    };

    var renderData = (array, select) => {
        let row = '<option disable value="">Chọn</option>';
        array.forEach((element) => {
            row += `<option data-id="${element.code}" value="${element.name}">${element.name}</option>`;
        });
        document.querySelector("#" + select).innerHTML = row;
    };

    $("#city").change(() => {
        // Kiểm tra nếu giá trị là "Chọn" thì không gọi API cho huyện/xã
        if ($("#city").val() === "") {
            $("#district").html('<option value="">Chọn</option>');
            $("#ward").html('<option value="">Chọn</option>');
        } else {
            callApiDistrict(
                host + "p/" + $("#city").find(":selected").data("id") + "?depth=2"
            );
        }
        printResult();
    });

    $("#district").change(() => {
        // Kiểm tra nếu giá trị là "Chọn" thì không gọi API cho xã
        if ($("#district").val() === "") {
            $("#ward").html('<option value="">Chọn</option>');
        } else {
            callApiWard(
                host +
                "d/" +
                $("#district").find(":selected").data("id") +
                "?depth=2"
            );
        }
        printResult();
    });

    $("#ward").change(() => {
        printResult();
    });

    var printResult = () => {
        if (
            $("#district").find(":selected").data("id") != "" &&
            $("#city").find(":selected").data("id") != "" &&
            $("#ward").find(":selected").data("id") != ""
        ) {
            let result =
                $("#city option:selected").text() +
                " | " +
                $("#district option:selected").text() +
                " | " +
                $("#ward option:selected").text();
            $("#result").text(result);
        }
    };

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
            const paymentMethod = document.querySelector('input[name="payment"]:checked').nextElementSibling.textContent.trim();

            delete order.orderInfoDTO;
            order.orderInfoDTO = {
                recipient: recipient,
                phone: phone,
                address: address,

            };
            // payment: paymentMethod


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
</script>