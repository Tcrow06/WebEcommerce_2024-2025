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
        <%--        <c:forEach items="${addressList}" var="address">--%>
        <%--          <div class="card mb-3">--%>
        <%--            <div class="card-body">--%>
        <%--              <h5>${address.fullName}</h5>--%>
        <%--              <p>${address.phoneNumber}</p>--%>
        <%--              <p>${address.addressLine}, ${address.city}, ${address.province}</p>--%>
        <%--              <div class="row">--%>
        <%--                <div class="col-4">--%>
        <%--                  <button type="button" class="btn btn-link text-decoration-none" onclick="setAsDefault(${address.id})">Thiết lập mặc định</button>--%>
        <%--                </div>--%>
        <%--                <div class="col-4">--%>
        <%--                  <button type="button" class="btn btn-link text-decoration-none" onclick="editAddress(${address.id})">Cập nhật</button>--%>
        <%--                </div>--%>
        <%--                <div class="col-4">--%>
        <%--                  <button type="button" class="btn btn-link text-decoration-none text-danger" onclick="deleteAddress(${address.id})">Xóa</button>--%>
        <%--                </div>--%>
        <%--              </div>--%>
        <%--            </div>--%>
        <%--          </div>--%>
        <%--        </c:forEach>--%>



        <div class="card mb-3" id="defaultAddress">
          <div class="card-body">
            <h5>
              Nguyễn Công Quý
              <span class="badge bg-primary-color ms-2">Mặc định</span>
            </h5>
            <p>+84 976 870 127</p>
            <p>Số 122 Đường 16, Tân Thạnh Đông, Củ Chi, Tp. Hồ Chí Minh</p>
            <div class="row">
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none"
                        onclick="setAsDefault(this)">
                  Thiết lập mặc định
                </button>
              </div>
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none"
                        onclick="editAddress()">
                  Cập nhật
                </button>
              </div>
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none text-danger"
                        onclick="deleteAddress()">
                  Xóa
                </button>
              </div>
            </div>
          </div>
        </div>


        <div class="card mb-3">
          <div class="card-body">
            <h5>Nguyễn Công Quý</h5>
            <p>+84 976 870 127</p>
            <p>Số 77 Tân Hòa 2, Phường Hiệp Phú, Thủ Đức, Tp. Hồ Chí Minh</p>
            <div class="row">
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none"
                        onclick="setAsDefault()">Thiết lập mặc định</button>
              </div>
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none"
                        onclick="editAddress()">Cập nhật</button>
              </div>
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none text-danger"
                        onclick="deleteAddress()">Xóa</button>
              </div>
            </div>
          </div>
        </div>

        <div class="card mb-3">
          <div class="card-body">
            <h5>Nguyễn Công Quý</h5>
            <p>+84 976 870 127</p>
            <p>Số 112 Đường Trần Đại Nghĩa, Thị trấn Thới Bình, Huyện Thới Bình, Cà Mau</p>
            <div class="row">
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none"
                        onclick="setAsDefault()">Thiết lập mặc định</button>
              </div>
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none"
                        onclick="editAddress()">Cập nhật</button>
              </div>
              <div class="col-4">
                <button type="button" class="btn btn-link text-decoration-none text-danger"
                        onclick="deleteAddress()">Xóa</button>
              </div>
            </div>
          </div>
        </div>


        <!-- Nút thêm địa chỉ mới -->
        <div class="d-flex justify-content-center mt-3">
          <button type="button" class="btn btn-primary" onclick="toggleNewAddressForm()">Thêm địa chỉ mới</button>
        </div>

        <!-- Form thêm địa chỉ mới, mặc định ẩn đi -->
        <div id="newAddressForm" class="mt-4" style="display: none;">
          <h5 style="font-weight: bold;">Thêm địa chỉ mới</h5>
          <hr/>
          <form>
            <div class="mb-3">
              <label for="specificAddress" class="form-label" style="font-weight: bold;">Địa chỉ cụ thể</label>
              <input type="text" class="form-control" id="specificAddress" required>
            </div>
            <div class="mb-3">
              <label for="ward" class="form-label" style="font-weight: bold;">Xã/Phường/Thị trấn</label>
              <input type="text" class="form-control" id="ward" required>
            </div>
            <div class="mb-3">
              <label for="district" class="form-label" style="font-weight: bold;">Quận/Huyện</label>
              <input type="text" class="form-control" id="district" required>
            </div>
            <div class="mb-3">
              <label for="city" class="form-label" style="font-weight: bold;">Tỉnh/Thành phố</label>
              <input type="text" class="form-control" id="city" required>
            </div>
            <!-- Nút lưu và quay về -->
            <div class="d-flex justify-content-end">
              <button type="button" class="btn btn-secondary me-2"
                      onclick="toggleNewAddressForm()">Quay về</button>
              <button type="button" class="btn btn-success" onclick="">Lưu</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  function setAsDefault(button) {
    // Xóa thẻ "Mặc định" từ tất cả địa chỉ
    document.querySelectorAll('.badge.bg-primary-color').forEach(badge => {
      badge.remove();
    });

    // Thêm thẻ "Mặc định" cho địa chỉ đã chọn
    const cardBody = button.closest('.card-body');
    const defaultBadge = document.createElement('span');
    defaultBadge.className = 'badge bg-primary-color ms-2';
    defaultBadge.textContent = 'Mặc định';

    cardBody.querySelector('h5').appendChild(defaultBadge);
  }


  function editAddress(addressId) {
    // Gọi modal khác hoặc chuyển hướng đến trang cập nhật địa chỉ
    alert("Chỉnh sửa địa chỉ cho ID: " + addressId);
  }

  function deleteAddress(addressId) {
    // Gọi API hoặc xử lý logic để xóa địa chỉ
    if (confirm("Bạn có chắc chắn muốn xóa địa chỉ này?")) {
      alert("Xóa địa chỉ cho ID: " + addressId);
    }
  }

  function toggleNewAddressForm() {
    // Lấy modal và form thêm địa chỉ mới
    const modal = document.getElementById('form2Modal');
    const newAddressForm = document.getElementById('newAddressForm');

    // Chuyển đổi hiển thị form và mở rộng modal
    if (newAddressForm.style.display === 'none') {
      newAddressForm.style.display = 'block';
      modal.classList.add('show-expanded');
    } else {
      newAddressForm.style.display = 'none';
      modal.classList.remove('show-expanded');
    }
  }

</script>