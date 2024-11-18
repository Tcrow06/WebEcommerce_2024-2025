<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ConfirmPassword Page</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/static/auth/style.css"/>" />
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">
            <form id="verify-form" class="sign-in-form" method="post" >
                <h2 class="title">Đổi mật khẩu</h2>
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}" role="alert" id="register-error-message">
                            ${message}
                    </div>
                </c:if>

                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Mật khẩu" name="password" required/>
                </div>

                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Nhập lại mật khẩu" name="repassword" required/>
                </div>

                <button class="btn" type="submit">Xác nhận</button>
            </form>
        </div>
    </div>
</div>
<script>
    // Lấy URL hiện tại
    const currentUrl = window.location.href;

    // Cập nhật action của form để trỏ đến URL hiện tại
    const form = document.getElementById('verify-form');
    form.action = currentUrl;
</script>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
