<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value='/static/admin/assets/css/style.css'/>" />

<style>
  .col-lg-3.col-md-3.d-flex.justify-content-end.align-items-center.gap-4,
  .header__menu.mobile-menu,
  .header__logo {
    display: none !important;
  }

  .form-group label {
    font-weight: bold;
  }

   #form2Modal .modal-dialog {
     max-width: 50%;
   }

  .badge.bg-primary-color {
    background-color: #ff9f43;
    color: white;
    padding: 0.2em 0.5em;
    font-weight: normal;
    border-radius: 0.25em;
    font-size: 0.85em;
  }

  .btn-address {
    color: #007bff;
    text-decoration: none;
    padding: 5px 10px;
    border-radius: 5px;
    transition: color 0.3s ease, background-color 0.3s ease;
  }


  /* Đặt độ rộng mặc định của modal */
  #form2Modal .modal-dialog {
    transition: max-width 0.7s ease;
    max-width: 600px;
  }

  /* Đặt độ rộng modal khi form thêm địa chỉ mới hiển thị */
  #form2Modal.show-expanded .modal-dialog {
    max-width: 680px;
    /* Điều chỉnh độ rộng này theo ý bạn */
  }

  .title-content {
    font-weight: bold;
  }
</style>

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
              <img src="<c:url value="/static/admin/assets/img/customer/customer5.jpg"/>" alt="img" id="blah">
              <div class="profileupload">
                <input type="file" id="imgInp">
                <a href="javascript:void(0);"><img src="<c:url value="/static/admin/assets/img/icons/edit-set.svg"/>"
                                                   alt="img"></a>
              </div>
            </div>
            <div class="profile-contentname">
              <h2 style="margin-top: 15px">Nguyễn Công Quý</h2>
              <h4>Cập nhật thông tin cá nhân và chi tiết địa chỉ của bạn.</h4>
              <a href="javascript:void(0);"
                 type="button" class="btn-address"
                 data-bs-toggle="modal"
                 data-bs-target="#form2Modal"
                 style="padding: 10px 0">Địa chỉ của bạn</a>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Họ và Tên</label>
            <input type="text" placeholder="Nguyễn Công Quý">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Email</label>
            <input type="text" placeholder="nguyencongquy296@gmail.com">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Chưa biết để gì</label>
            <input type="text" placeholder="Cái gì đó chưa nghỉ ra...">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Điện thoại</label>
            <input type="text" placeholder="+976870127">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Tên đăng nhập</label>
            <input type="text" placeholder="+1452 876 5432">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label class="title-box">Mật khẩu</label>
            <div class="pass-group">
              <input type="password" class=" pass-input">
            </div>
          </div>
        </div>
        <div class="col-12">
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <a href="javascript:void(0);" class="btn btn-cancel">Tiếp tục mua sắm</a>
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
  function updateOrderInfo() {

  }

  function toggleNewAddressForm() {
    const form = document.getElementById('newAddressForm');
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
  }


</script>