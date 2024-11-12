<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                                <li>01. Vanilla salted caramel <span>$ 300.0</span></li>
                                <li>02. German chocolate <span>$ 170.0</span></li>
                                <li>03. Sweet autumn <span>$ 170.0</span></li>
                                <li>04. Cluten free mini dozen <span>$ 110.0</span></li>
                            </ul>
                            <ul class="checkout__total__all">
                                <li>Số tiền được giảm <span>$750.99</span></li>
                                <li>Tổng tiền <span>$750.99</span></li>
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
                            <button type="submit" class="site-btn">Đặt hàng</button>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <h6 class="checkout__title">Thông tin giao hàng</h6>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-lg-8"> -->
                        <div class="checkout__input">
                            <p>Họ và tên<span>*</span></p>
                            <input class="" type="text" />
                            <!-- </div> -->
                            <!-- </div> -->
                        </div>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-lg-8"> -->
                        <div class="checkout__input">
                            <p>Số điện thoại<span>*</span></p>
                            <input type="text" />
                            <!-- </div> -->
                            <!-- </div> -->
                        </div>
                        <div class="checkout__input">
                            <p>Tỉnh/Thành phố<span>*</span></p>
                            <select id="city" class="col-lg-12 py-2">
                                <option value="" selected>Chọn tỉnh thành</option>
                            </select>
                        </div>
                        <br />
                        <div class="checkout__input">
                            <p>Quận/Huyện<span>*</span></p>
                            <select id="district" class="col-lg-12 py-2">
                                <option value="" selected>Chọn quận huyện</option>
                            </select>
                        </div>
                        <br />
                        <div class="checkout__input">
                            <p>Phường/Xã<span>*</span></p>
                            <select id="ward" class="col-lg-12 py-2">
                                <option value="" selected>Chọn phường xã</option>
                            </select>
                        </div>
                        <br />
                        <div class="checkout__input">
                            <p>Số nhà, tên đường<span>*</span></p>
                            <input type="text" />
                        </div>
                    </div>
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
</script>