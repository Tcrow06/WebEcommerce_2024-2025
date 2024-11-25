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
  <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>"
        integrity="sha384-k6RqeWeci5ZR/Lv4MR0sA0FfDOMPhuv1De4VfM1g2B1iI4+6jCW51e7no4mr10s5"
        crossorigin="anonymous">

  <%--API địa chỉ--%>
  <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"/>"
          referrerpolicy="no-referrer">
  </script>
  <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"/>"></script>

  <!-- Bootstrap -->
  <link href="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="<c:url value="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"/>"
          integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
          crossorigin="anonymous"></script>
  <script src="<c:url value="https://cdn.jsdelivr.net/npm/sweetalert2@11"/>"></script>

  <!-- Google Font -->
  <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap" rel="stylesheet">

  <!-- Custom CSS -->
  <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/font-awesome.min.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>" type="text/css">
  <link rel="stylesheet" href="<c:url value='/static/css/toast.css'/>" type="text/css"/>
  <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>">


  
</head>

<body>

<!-- Header -->
<%@ include file="../common/web/header.jsp"%>

<dec:body>

</dec:body>

<!-- Footer -->
<%@ include file="../common/web/footer.jsp"%>

<!-- Js Plugins -->
<script src="<c:url value='/static/js/bootstrap.min.js'/>"></script>
</body>

</html>
