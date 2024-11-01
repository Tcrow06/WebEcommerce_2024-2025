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

    <!-- Google Font -->
    <link href="<c:url value="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"/>"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/font-awesome.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/elegant-icons.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/magnific-popup.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/nice-select.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/owl.carousel.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/slicknav.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>" type="text/css">
</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<%@ include file="../common/web/header.jsp"%>

<dec:body>

</dec:body>

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
<script src="<c:url value='/static/web/js/token/refreshToken.js'/> "></script>
<script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/js/jquery.nice-select.min.js"/>"></script>
<script src="<c:url value="/static/js/jquery.nicescroll.min.js"/>"></script>
<script src="<c:url value="/static/js/jquery.magnific-popup.min.js"/>"></script>
<script src="<c:url value="/static/js/jquery.countdown.min.js"/>"></script>
<script src="<c:url value="/static/js/jquery.slicknav.js"/>"></script>
<script src="<c:url value="/static/js/mixitup.min.js"/>"></script>
<script src="<c:url value="/static/js/owl.carousel.min.js"/>"></script>
<script src="<c:url value="/static/js/main.js"/>"></script>

<%--<script src="<c:url value='/static/auth/js/sendDirection.js'/> " type="text/javascript"></script>--%>
</body>

</html>
