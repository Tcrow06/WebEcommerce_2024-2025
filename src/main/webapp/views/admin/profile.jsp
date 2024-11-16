<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
              <h2>Nguyễn Công Quý</h2>
              <h4>Cập nhật thông tin cá nhân và chi tiết địa chỉ của bạn.</h4>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label>Họ và Tên</label>
            <input type="text" placeholder="Nguyễn Công Quý">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label>Email</label>
            <input type="text" placeholder="nguyencongquy296@gmail.com">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label>Chưa biết để gì</label>
            <input type="text" placeholder="Cái gì đó chưa nghỉ ra...">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label>Phone</label>
            <input type="text" placeholder="+976870127">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label>Tên đăng nhập</label>
            <input type="text" placeholder="+1452 876 5432">
          </div>
        </div>
        <div class="col-lg-6 col-sm-12">
          <div class="form-group">
            <label>Mật khẩu</label>
            <div class="pass-group">
              <input type="password" class=" pass-input">
            </div>
          </div>
        </div>
        <div class="col-12">
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <a href="javascript:void(0);" class="btn btn-cancel" data-bs-toggle="modal" data-bs-target="#form2Modal">Địa chỉ</a>
            <a href="javascript:void(0);" class="btn btn-submit me-2">Lưu</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<%----%>