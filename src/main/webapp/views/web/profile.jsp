<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value='/static/admin/assets/css/style.css'/>" />

<style>
  /* Reset các phần cũ */
  .col-lg-3.col-md-3.d-flex.justify-content-end.align-items-center.gap-4,
  .header__menu.mobile-menu,
  .header__logo {
    display: none !important;
  }

  body {
    font-family: "Roboto", Arial, sans-serif;
    background: linear-gradient(to bottom, #f8f9fa, #e9ecef);
    color: #343a40;
    line-height: 1.6;
  }

  .card {
    border-radius: 12px;
    box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
    background: #fff;
    margin: 20px;
    padding: 20px;
    transition: transform 0.3s, box-shadow 0.3s;
  }

  .card:hover {
    transform: translateY(-10px);
    box-shadow: 0 15px 25px rgba(0, 0, 0, 0.2);
  }

  .page-header {
    background: linear-gradient(90deg, #ff7e5f, #feb47b);
    color: #fff;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }

  .page-title h4 {
    margin: 0;
    font-size: 1.8rem;
  }

  .page-title h6 {
    font-size: 1rem;
    margin-top: 5px;
    opacity: 0.8;
  }

  .profile-contentimg img {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    border: 5px solid #f8f9fa;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
    object-fit: cover; /* Giữ tỉ lệ và cắt phần thừa */
  }

  .profileupload input[type="file"] {
    display: none;
  }

  .profileupload a {
    position: absolute;
    bottom: -10px;
    right: 10px;
    background: #ff9f43;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 3px 5px rgba(0, 0, 0, 0.1);
    transition: background 0.3s ease, transform 0.3s ease;
  }

  .profileupload a:hover {
    background: #f8c291;
    transform: scale(1.1);
  }

  .login-with-google-btn, .login-with-facebook-btn {
    display: inline-block;
    padding: 10px 15px;
    font-size: 14px;
    color: #fff;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.3s ease;
  }

  .login-with-google-btn {
    background-color: #4285f4;
    margin-right: 10px;
  }

  .login-with-google-btn:hover {
    background-color: #357ae8;
    transform: translateY(-2px);
  }

  .login-with-facebook-btn {
    background-color: #3b5998;
  }

  .login-with-facebook-btn:hover {
    background-color: #2d4373;
    transform: translateY(-2px);
  }

  .form-group {
    margin-bottom: 20px;
  }

  .form-group label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
    color: #343a40;
  }

  input[type="text"], input[type="password"] {
    width: 100%;
    padding: 10px 15px;
    border: 1px solid #ced4da;
    border-radius: 8px;
    transition: border-color 0.3s, box-shadow 0.3s;
  }

  input[type="text"]:focus, input[type="password"]:focus {
    border-color: #ff9f43;
    box-shadow: 0 0 8px rgba(255, 159, 67, 0.4);
  }

  .btn-address {
    background-color: #ff9f43;
    color: #fff;
    padding: 10px 20px;
    border-radius: 6px;
    text-transform: uppercase;
    font-weight: bold;
    transition: background-color 0.3s, transform 0.3s;
  }

  .btn-address:hover {
    background-color: #ff7846;
    transform: translateY(-2px);
  }

  .btn-submit {
    background-color: #28a745;
    color: #fff;
    padding: 10px 15px;
    border-radius: 6px;
    font-weight: bold;
    transition: background-color 0.3s ease, transform 0.3s ease;
  }

  .btn-submit:hover {
    background-color: #218838;
    transform: scale(1.05);
  }

  .btn-cancel {
    background-color: #6c757d;
    color: #fff;
    padding: 10px 15px;
    border-radius: 6px;
    transition: background-color 0.3s ease;
  }

  .btn-cancel:hover {
    background-color: #5a6268;
  }

  #otpModal {
    display: none; /* Hiển thị khi cần */
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }

  #otpModal div {
    background: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
    text-align: center;
    width: 300px;
    animation: slideIn 0.5s ease-out; /* Hiệu ứng */
  }

  @keyframes slideIn {
    from {
      transform: translateY(-50%);
      opacity: 0;
    }
    to {
      transform: translateY(0);
      opacity: 1;
    }
  }

  .card-body button {
    transition: color 0.3s, transform 0.3s;
  }

  .card-body button:hover {
    color: #ff7846;
    transform: scale(1.05);
  }

  .modal-content {
    border-radius: 12px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
  }

  .modal-header {
    background: linear-gradient(to right, #ff7e5f, #feb47b);
    color: #fff;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
  }

  .modal-body {
    padding: 20px;
  }

  .card-body {
    border: 1px solid #f1f1f1;
    border-radius: 8px;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  .card-body:hover {
    transform: translateY(-5px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  }


</style>


<%--<div class="notifications">--%>
<%--  <h2>Công Quý</h2>--%>
<%--</div>--%>

<div class="content">
  <div class="page-header">
    <div class="page-title">
      <h4>Trang cá nhân</h4>
      <h6>Quản lý trang cá nhân</h6>
      <input type="hidden" id="customerId" value="${id}">
    </div>
  </div>

  <div class="card">
    <div class="card-body">
      <div class="profile-set">
        <div class="profile-head">
        </div>
        <div class="profile-top">
          <div class="profile-content">
            <div class="profile-contentimg">
              <c:if test="${not empty userResponse.avatar}">
                <img src="" alt="img" id="avatar">
                <input type="hidden" value="${userResponse.avatar}" id="avatarInput">

                <script>
                  // Lấy giá trị avatar từ input ẩn
                  const avatarPath = document.getElementById('avatarInput').value;
                  const avatarImg = document.getElementById('avatar');

                  // Kiểm tra nếu avatarPath bắt đầu bằng "https:"
                  if (avatarPath.startsWith("https:")) {
                    avatarImg.src = avatarPath;
                  } else {
                    avatarImg.src = '<c:url value="/api-image?path="/>' + avatarPath;
                  }
                </script>
              </c:if>
              <c:if test="${empty userResponse.avatar}">
                <img src='<c:url value="/static/img/avatar/user.png"/>' alt="User Profile" width="32" height="32" class="rounded-circle user-avatar">
              </c:if>
              <div class="profileupload">
                <input type="file" id="imgInp">
                <a href="javascript:void(0);"><img src="<c:url value="/static/admin/assets/img/icons/edit-set.svg"/>"
                                                   alt="img"></a>
              </div>
            </div>
            <div class="profile-contentname">
              <h2 style="margin-top: 15px">${userResponse.name}</h2>
              <h4>Cập nhật thông tin cá nhân và chi tiết địa chỉ của bạn.</h4>
              <a href="javascript:void(0);"
                 type="button" class="btn-address"
                 data-bs-toggle="modal"
                 data-bs-target="#form2Modal"
                 style="padding: 10px 0">Địa chỉ của bạn</a>
            </div>
          </div>
          <div class="ms-auto" style="right: 0 !important;">
            <button type="button" class="login-with-google-btn" id="withGoogle">
              Liên kết Google
            </button>
            <button type="button" class="login-with-facebook-btn" id="withFacebook">
              Liên kết Facebook
            </button>
          </div>
        </div>
      </div>
      <div class="row">
        <div id="otpModal">
          <div>
            <h3>Nhập mã OTP</h3>
            <span id="otp-error" style="color: red; display: none;">Bạn chưa nhập mã OTP</span>
            <input type="text" id="otp-input" data-key="otp" placeholder="Nhập mã OTP" />
            <button id="submit-otp" style="margin-top: 10px;">Gửi</button>
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Họ và Tên</label>
            <input type="text" data-key="name" placeholder="Enter your name" value="${userResponse.name}">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Email</label>
            <input type="text" data-key="email" placeholder="enter your email" value="${userResponse.email}">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Điện thoại</label>
            <input type="text" data-key="phone" placeholder="enter your phone number" value="${userResponse.phone}">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <input type="hidden" data-key="" placeholder="oke">
            <input type="hidden" data-key="id" value="${id}">

          </div>
        </div>

        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Tên đăng nhập</label>
            <c:if test="${not empty accountResponse}">
              <input type="text"  value="${accountResponse.userName}" readonly>
            </c:if>
            <c:if test="${empty accountResponse}">
              <input type="text" placeholder="enter your password" value="Tên đăng nhập" readonly>
            </c:if>
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Mật khẩu</label>
            <div class="passs-group">
              <input type="password" data-key="pass" class=" pass-input" placeholder="">
            </div>
          </div>
        </div>
        <div class="col-12">
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <a href="<c:url value='/danh-sach-san-pham?page=1&maxPageItem=9'/> " class="btn btn-cancel">Tiếp tục mua sắm</a>
            <a href="javascript:void(0);" class="btn btn-submit me-2">Lưu</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<%----%>


<div class="modal fade" id="form2Modal" tabindex="-1" aria-labelledby="form2ModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content shadow">
      <div class="modal-header">
        <h4 class="modal-title" id="form2ModalLabel">Địa chỉ của tôi</h4>
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
                          onclick="setDefaultOrderInfo(this)">
                    Thiết lập mặc định
                  </button>
                </div>
                <div class="col-4">
                  <button type="button" class="btn btn-link text-decoration-none"
                          onclick="updateOrderInfo()">
                    Cập nhật
                  </button>
                </div>
                <div class="col-4">
                  <button type="button" class="btn btn-link text-decoration-none text-danger"
                          onclick="deleteOrderInfo(this)">
                    Xóa
                  </button>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>


        <!-- Nút thêm địa chỉ mới -->
        <div class="d-flex justify-content-center mt-3">
          <button type="button" class="btn btn-primary" onclick="toggleNewAddressForm()">Thêm địa chỉ mới</button>
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

  // Hàm thêm mới địa chỉ
  function saveOrderInfo() {
    // Lấy giá trị từ các trường nhập liệu
    const customerId = document.getElementById('customerId').value.trim();
    const recipientName = document.getElementById('recipientName').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const city = document.getElementById('city').value;
    const district = document.getElementById('district').value;
    const ward = document.getElementById('ward').value;
    const specificAddress = document.getElementById('specificAddress').value.trim();

    // Kiểm tra nếu các trường bắt buộc chưa được điền đầy đủ
    if (!recipientName || !phone || !city || !district || !ward || !specificAddress) {
      Swal.fire({
        icon: 'warning',
        title: 'Thiếu thông tin',
        text: 'Vui lòng điền đầy đủ tất cả các trường trước khi lưu.'
      });
      return;
    }

    // Chuẩn bị dữ liệu để gửi

    const address = {
      city: city,
      district: district,
      commune: ward,
      concrete: specificAddress
    };

    const orderInfo = {
      recipient: recipientName,
      phone: phone,
      address: address,
      customerId: customerId
    };

    // Sử dụng AJAX để gửi yêu cầu lưu thông tin đơn hàng
    $.ajax({
      url: '/api-them-thong-tin-giao-hang',
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(orderInfo),
      success: function (response) {
        // Kiểm tra kết quả trả về từ API
        if (response.success) {

          Swal.fire({
            icon: 'success',
            title: 'Thành công!',
            text: 'Địa chỉ đã được lưu thành công.'
          }).then(() => {
            // Tải lại trang và mở lại modal sau khi Swal được đóng
            location.reload();
          });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Có lỗi xảy ra',
            text: 'Không thể lưu thông tin, vui lòng thử lại.'
          });
        }
      },
      error: function (error) {
        Swal.fire({
          icon: 'error',
          title: 'Lỗi mạng',
          text: 'Không thể kết nối đến máy chủ, vui lòng kiểm tra lại.'
        });
        console.error('Error saving order info:', error);
      }
    });
  }


  // Hàm set địa chỉ mặc định
  function setDefaultOrderInfo(button) {
    const cardBody = $(button).closest('.card-body');
    const orderId = cardBody.find('input[type="hidden"]').val();

    const data = {
      id: orderId
    };

    $.ajax({
      url: '/api-dat-dia-chi-mac-dinh',
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function(response) {
        if (response.success) {
          Swal.fire({
            icon: 'success',
            title: 'Thành công!',
            text: 'Địa chỉ đã được thiết lập làm mặc định.'
          }).then(() => {
            location.reload();
          });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Có lỗi xảy ra',
            text: 'Không thể thiết lập địa chỉ mặc định, vui lòng thử lại.'
          });
        }
      },
      error: function(error) {
        Swal.fire({
          icon: 'error',
          title: 'Lỗi mạng',
          text: 'Không thể kết nối đến máy chủ, vui lòng kiểm tra lại.'
        });
        console.error('Error setting default order info:', error);
      }
    });
  }

  // Hàm xóa thông tin địa chỉ
  function deleteOrderInfo(element) {
    const cardBody = $(element).closest('.card-body');
    const orderId = cardBody.find('input[type="hidden"]').val();

    const data = {
      id: orderId
    };

    $.ajax({
      url: '/api-xoa-thong-tin-giao-hang',
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function(response) {
        // Kiểm tra kết quả trả về từ API
        if (response.success) {
          Swal.fire({
            icon: 'success',
            title: 'Thành công!',
            text: 'Địa chỉ đã được xóa thành công.'
          }).then(() => {
            cardBody.parent().remove();
          });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Có lỗi xảy ra',
            text: 'Không thể xóa địa chỉ, vui lòng thử lại.'
          });
        }
      },
      error: function(error) {
        Swal.fire({
          icon: 'error',
          title: 'Lỗi mạng',
          text: 'Không thể kết nối đến máy chủ, vui lòng kiểm tra lại.'
        });
        console.error('Error deleting order info:', error);
      }
    });
  }

  // Hàm cập nhật thông tin địa chỉ
  let notifications = document.querySelector('.notifications');
  const googleLoginBtn = document.getElementById('withGoogle');
  const facebookLoginBtn = document.getElementById('withFacebook');

  googleLoginBtn.onclick = function() {
    let type = 'success';
    let icon = 'fa-solid fa-circle-check';
    let title = 'Success';
    let text = 'Đã liên kết với Google thành công.';
    createToast(type, icon, title, text);
  }

  const getUserInfo = () => {
    const userData = {};
    const inputs = document.querySelectorAll('input[data-key]');
    inputs.forEach(input => {
      const key = input.getAttribute('data-key');
      if (key && key !== 'pass') {
        userData[key] = input.value;
      }
    });
    return userData;
  };


  document.querySelector('.btn-submit').addEventListener('click', () => {
    const name = document.querySelector('input[data-key="name"]').value.trim();
    const email = document.querySelector('input[data-key="email"]').value.trim();
    const phone = document.querySelector('input[data-key="phone"]').value.trim();

    if (!name || !email || !phone) {
      Swal.fire({
        icon: 'warning',
        title: 'Thiếu thông tin',
        text: 'Vui lòng điền đầy đủ Họ tên, Email và Số điện thoại trước khi lưu.'
      });
      return;
    }
    const passwordInput = document.querySelector('input[data-key="pass"]').value.trim() || "";
    const id = document.querySelector('input[data-key="id"]').value.trim();
    const otpModal = document.getElementById('otpModal');
    const otpError = document.getElementById('otp-error');
    const otpInput = document.getElementById('otp-input');
    let otp = "";

    if (passwordInput !== "") {
      const sendOtp = {
        id: id,
        email: document.querySelector('input[data-key="email"]').value.trim()
      }
      fetch('/api/send-otp', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(sendOtp),
      })
              .then(response => {
                if (response.ok) {
                  otpModal.style.display = 'flex';
                } else {
                  throw new Error('Không thể gửi OTP, vui lòng thử lại.');
                }
              })
              .catch(error => {
                console.error('Lỗi gửi OTP:', error);
                alert('Không thể gửi OTP, vui lòng thử lại.');
              });

      document.getElementById('submit-otp').addEventListener('click', () => {
        if (!otpInput.value.trim()) {
          otpError.style.display = 'block';
          return;
        }
        otp = otpInput.value.trim();
        otpError.style.display = 'none';
        otpModal.style.display = 'none';
        sendDataToServer(passwordInput, otp);
      });
    } else {
      sendDataToServer(passwordInput, otp);
    }
  });

  function sendDataToServer(passwordInput, otp) {
    const userInfo = getUserInfo();
    const accountInfo = {
      pass: passwordInput,
    };

    const payload = {
      otp: otp,
      user: userInfo,
      account: accountInfo,
    };

    fetch('/api/update-user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    })
            .then(response => {
              if (response.ok) {
                return response.json();
              }
              throw new Error('Có lỗi xảy ra trong quá trình gửi dữ liệu.');
            })
            .then(data => {
              alert('Cập nhật thành công!');
              window.location.reload();
            })
            .catch(error => {
              console.error('Lỗi:', error);
              alert('Không thể cập nhật thông tin, vui lòng thử lại.');
            });
  }




  function updateOrderInfo() {

  }

  function toggleNewAddressForm() {
    const form = document.getElementById('newAddressForm');
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
  }

  function createToast(type, icon, title, text){
    let newToast = document.createElement('div');
    newToast.innerHTML =
            '<div class="toast ' + type + '">' +
            '<i class="' + icon + '"></i>' +
            '<div class="content">' +
            '<div class="title">' + title + '</div>' +
            '<span>' + text + '</span>' +
            '</div>' +
            '<i class="close fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>' +
            '</div>';

    notifications.appendChild(newToast);
    newToast.timeOut = setTimeout(() => newToast.remove(), 5000)
  }



</script>