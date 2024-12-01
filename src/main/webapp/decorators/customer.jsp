<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
  <meta charset="UTF-8">
  <meta name="description" content="Male_Fashion Template">
  <meta name="keywords" content="Male_Fashion, unica, creative, html">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Male-Fashion | Template</title>

  <!-- Font Awesome -->
<%--  <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>"
        integrity="sha384-k6RqeWeci5ZR/Lv4MR0sA0FfDOMPhuv1De4VfM1g2B1iI4+6jCW51e7no4mr10s5"
        crossorigin="anonymous">--%>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        crossorigin="anonymous">

  <!-- jQuery -->
  <script src="<c:url value="https://code.jquery.com/jquery-3.6.0.min.js"/>"></script>

  <!-- Bootstrap -->
  <link href="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"/>"
          integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
          crossorigin="anonymous"></script>

  <!-- Google Font -->
  <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap" rel="stylesheet">

  <!-- Custom CSS -->
  <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/font-awesome.min.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/elegant-icons.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/magnific-popup.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/nice-select.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/owl.carousel.min.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/slicknav.min.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>" type="text/css">

  <!-- Additional Scripts -->
  <script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
  <script src="<c:url value='/static/web/js/token/refreshToken.js'/> "></script>
  <script src="<c:url value="https://cdn.jsdelivr.net/npm/sweetalert2@11"/>"></script>
</head>

<body>
<!-- Page Preloader -->
<div id="preloder">
  <div class="loader"></div>
</div>

<!-- Header -->
<%@ include file="../common/web/header.jsp"%>

<dec:body>
</dec:body>

<!-- Footer -->
<%@ include file="../common/web/footer.jsp"%>

<!-- Search Begin -->
<div class="search-model">
  <div class="h-100 d-flex align-items-center justify-content-center">
    <div class="search-close-switch">+</div>
    <form class="search-model-form">
      <input type="text" id="search-input" placeholder="Search here.....">
    </form>
  </div>
</div>
<!-- Search End -->

<!-- Js Plugins -->
<script src="<c:url value='/static/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/static/js/jquery.nice-select.min.js'/>"></script>
<script src="<c:url value='/static/js/jquery.nicescroll.min.js'/>"></script>
<script src="<c:url value='/static/js/jquery.magnific-popup.min.js'/>"></script>
<script src="<c:url value='/static/js/jquery.countdown.min.js'/>"></script>
<script src="<c:url value='/static/js/jquery.slicknav.js'/>"></script>
<script src="<c:url value='/static/js/mixitup.min.js'/>"></script>
<script src="<c:url value='/static/js/owl.carousel.min.js'/>"></script>
<script src="<c:url value='/static/js/main.js'/>"></script>
</body>

</html>
