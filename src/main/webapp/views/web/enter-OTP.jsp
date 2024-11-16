<%@include file="/common/taglib.jsp"%>
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
            <form action="#" class="sign-in-form">
                <h2 class="title">Nhập mã OTP</h2>

                <div class="input-field">
                    <i class="fas fa-key"></i>
                    <input type="text" placeholder="Mã OTP" />
                </div>

                <div class="button-group">
                    <button class="btn">Gửi lại OTP</button>
                    <button class="btn">Xác nhận</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
