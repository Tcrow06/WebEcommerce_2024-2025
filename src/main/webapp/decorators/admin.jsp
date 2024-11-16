<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <meta name="description" content="POS - Bootstrap Admin Template">
    <meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, invoice, html5, responsive, Projects">
    <meta name="author" content="Dreamguys - Bootstrap Admin Template">
    <meta name="robots" content="noindex, nofollow">
    <title>Dreams Pos admin template</title>

    <link rel="shortcut icon" type="image/x-icon" href="../static/admin/assets/img/favicon.jpg">

    <link rel="stylesheet" href="<c:url value="/static/admin/assets/css/bootstrap.min.css"/>">

    <%--   started Sales-list--%>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/static/admin/assets/img/favicon.jpg'/>"/>
    <link rel="stylesheet" href="<c:url value='/static/admin/assets/css/animate.css'/> " />
    <link rel="stylesheet" href="<c:url value='/static/admin/assets/plugins/select2/css/select2.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/static/admin/assets/css/bootstrap-datetimepicker.min.css'/>" />
    <link rel="stylesheet" href="<c:url value='/static/admin/assets/css/dataTables.bootstrap4.min.css'/>" />
    <link rel="stylesheet" href="<c:url value='/static/admin/assets/plugins/fontawesome/css/fontawesome.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/static/admin/assets/plugins/fontawesome/css/all.min.css'/>" />
    <link rel="stylesheet" href="<c:url value='/static/admin/assets/css/style.css'/>" />

</head>

<body>
<div id="global-loader">
    <div class="whirly-loader"> </div>
</div>

<div class="main-wrapper">


    <%@ include file="../common/admin/header.jsp"%>

    <%@ include file="../common/admin/nav.jsp"%>

    <div class="page-wrapper">
        <dec:body/>
    </div>

</div>


<script src="<c:url value='/static/admin/assets/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/js/feather.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/js/jquery.slimscroll.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/js/jquery.dataTables.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/js/dataTables.bootstrap4.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/js/bootstrap.bundle.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/plugins/select2/js/select2.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/plugins/sweetalert/sweetalert2.all.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/plugins/sweetalert/sweetalerts.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/js/script.js'/>"></script>
<script src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.10.2/mdb.min.js'/>"></script>
<script src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js'/>"></script>
<script src="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/static/admin/assets/js/bootstrap.bundle.min.js'/>"></script>
<script src="<c:url value='/static/web/js/token/refreshToken.js'/> "></script>

</body>

</html>


