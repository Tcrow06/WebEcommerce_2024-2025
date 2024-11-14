<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
  body {
    background-color: #f5f5f5;
    font-family: Arial, sans-serif;
  }

  .order-completion {
    max-width: 100%;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
  }

  .shop-info {
    display: flex;
    align-items: center;
    gap: 10px;
    padding-bottom: 15px;
    border-bottom: 1px solid #e0e0e0;
  }

  .shop-info button {
    font-size: 14px;
    padding: 5px 15px;
  }

  .product-info {
    display: flex;
    padding: 15px 0;
    border-bottom: 1px solid #e0e0e0;
  }

  .product-info img {
    width: 100px;
    height: 100px;
    border-radius: 8px;
    margin-right: 15px;
  }

  .product-details {
    flex: 1;
  }

  .product-price {
    text-align: right;
    color: #ff424e;
    font-weight: bold;
    font-size: 18px;
  }

  .total-price {
    display: flex;
    justify-content: flex-end;
    padding-top: 10px;
    color: #ff424e;
    font-weight: bold;
    font-size: 20px;
  }

  .review-section {
    color: #ff424e;
    text-align: center;
    padding-top: 10px;
    font-size: 14px;
  }

  .action-buttons {
    display: flex;
    justify-content: center;
    gap: 10px;
    margin-top: 20px;
  }

  .action-buttons .btn {
    padding: 10px 30px;
    font-size: 16px;
    border-radius: 8px;
  }

  .discount-price {
    color: #888;
    text-decoration: line-through;
    margin-right: 10px;
    font-size: 14px;
  }
</style>
<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="breadcrumb__text">
          <h4>Notification</h4>
          <div class="breadcrumb__links">
            <a href="<c:url value="/trang-chu"/>">Home</a>
            <span>Notification>Oder Success</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="side-bar">
  <div class="d-flex">
    <!-- Sidebar -->
    <div class="sidebar m-3">
      <div class="d-flex flex-column" style=" height:100%;">
        <nav class="nav flex-column">
          <a class="link-offset-2 m-2 link-underline link-underline-opacity-0" href="<c:url value="/thong-bao"/>" style = "color:#f53d2d" >Cập Nhật Đơn Hàng</a>
          <a class="link-offset-2 m-2 link-underline link-underline-opacity-0" href="<c:url value="/phieu-giam-gia"/>" style = "color:black" >Kho Voucher</a>
        </nav>
      </div>
    </div>

    <div class="order-completion">
      <!-- Shop Information -->
      <div class="shop-info">
        <a class="link-offset-2 link-underline link-underline-opacity-0" href="" style="color: #888;"><span>&lt; Trở lại</span></a>
        <span class="ms-auto text-success"><i class="bi bi-truck"></i> Giao hàng thành công</span>
      </div>

      <!-- Product Information -->
      <div class="product-info">
        <img src="/static/img/product/product-1.jpg" alt="Product Image">
        <div class="product-details">
          <h5>Thùng 100 Chiếc Khẩu Trang AK MASK 6D Pro Mask Cao Cấp - Khẩu Trang Phạm Thoại</h5>
          <p>Phân loại hàng: Đen, 200 Chiếc</p>
          <span>x1</span>
        </div>
        <div class="product-price">
          <span class="discount-price">₫280,000</span>₫160,000
        </div>
      </div>

      <!-- Total Price -->
      <div class="total-price">
        Thành tiền: ₫126,000
      </div>


      <!-- Action Buttons -->
      <div class="action-buttons">
        <button class="btn btn-danger">Đánh Giá</button>
        <button class="btn btn-outline-secondary">Liên Hệ Người Bán</button>
        <button class="btn btn-outline-secondary">Mua Lại</button>
      </div>
    </div>
  </div>
</section>