<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ConfirmPassword Page</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/static/auth/style.css'/>" />
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">
            <form id="verify-form" class="sign-in-form" method="post">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}" role="alert" id="register-error-message">
                            ${message}
                    </div>
                </c:if>
                <h2 class="title">Nhập mã OTP</h2>

                <div class="input-field">
                    <i class="fas fa-key"></i>
                    <input type="text" placeholder="Mã OTP" name="otp" required />
                </div>

                <div class="button-group">
                    <button class="btn" id="resend-otp">Gửi lại OTP</button>
                    <button class="btn" type="submit">Xác nhận</button>
                </div>
                <span> OTP sẽ hết hạn sau 180 giây </span>
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
