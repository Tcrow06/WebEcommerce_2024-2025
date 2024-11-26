<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="vi">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Lỗi hệ thống</title>

  <!-- Google font -->
  <link href="https://fonts.googleapis.com/css?family=Kanit:200" rel="stylesheet">

  <!-- Font Awesome Icon -->
  <link type="text/css" rel="stylesheet" href="<c:url value="/static/error/css/font-awesome.min.css"/>" />

  <!-- Custom stylesheet -->
  <link type="text/css" rel="stylesheet" href="<c:url value="/static/error/css/style.css"/>" />
</head>

<body>
<div id="notfound">
  <div class="notfound">
    <div class="notfound-404">
      <h1>404</h1>
    </div>
    <h2>Oops! Không tìm thấy trang</h2>
    <p>Đường dẫn vừa truy cập hiện không tồn tại trên hệ thống, nếu có vấn đề thắc mắc, vui lòng liên hệ
      theo các đường link bên dưới để Eleven Shop có thể hỗ trợ tốt hơn.
      <a href="<c:url value="/trang-chu"/>">Trở về trang chủ</a>
    </p>
    <div class="notfound-social">
      <!-- Facebook -->
      <a href="https://www.facebook.com/share/gGG2NTFfiFXXAYpi/?mibextid=LQQJ4d"><i class="fa fa-facebook"></i></a>
      <!-- Twitter -->
      <a href="#"><i class="fa fa-twitter"></i></a>
      <!-- Google -->
      <a href="https://mail.google.com/mail/?view=cm&fs=1&to=elevenfashionshop11@gmail.com&su=Support Needed&body=Xin chào, tôi cần hỗ trợ về..." target="_blank"><i class="fa fa-google-plus"></i></a>
    </div>
  </div>
</div>
</body>
</html>
