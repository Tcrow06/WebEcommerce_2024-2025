<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script
            src="<c:url value="https://kit.fontawesome.com/64d58efce2.js"/>"
            crossorigin="anonymous"
    ></script>
    <link href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/static/auth/style.css"/>" />
    <title>Đăng nhập và đăng ký</title>
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">

            <form action="<c:url value='/dang-nhap'/>" class="sign-in-form" method="post">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}" role="alert">
                            ${message}
                    </div>
                </c:if>
                <h2 class="title">Đăng nhập</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Tên đăng nhập" id="userName" name="userName" />
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Mật khẩu" id="password" name="password" />
                </div>
                <input type="hidden" name="action" value="login" />
                <input type="submit" value="Đăng nhập" class="btn solid" />
                <p class="social-text">Hoặc đăng nhập bằng phương thức khác</p>
                <div class="social-media">
                    <a href="#" class="social-icon">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a id="google-id" href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/three-party-login&response_type=code&client_id=166848038588-5bre3evlsm652tcp88lrogu6m189s5lb.apps.googleusercontent.com&approval_prompt=force" class="social-icon">
                        <i class="fab fa-google"></i>
                    </a>
                </div>
            </form>
            <form action="<c:url value='/dang-ky'/>" class="sign-up-form" method="post">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}" role="alert">
                            ${message}
                    </div>
                </c:if>
                <h2 class="title">Tạo tài khoản</h2>

                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Họ và tên" id="name" name="name"  />
                </div>

                <div class="input-field">
                    <i class="fas fa-phone"></i>
                    <input type="tel" placeholder="Số điện thoại" pattern="[0-9]{10}" id="phone" name="phone"  />
                </div>


                <div class="input-field">
                    <i class="fas fa-envelope"></i>
                    <input type="email" placeholder="Email" id="email" name="email" />
                </div>

                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Tên đăng nhập" name="userName"/>
                </div>

                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Mật khẩu" name="password" />
                </div>
                <input type="hidden" name="action" value="register" />
                <input type="submit" value="Đăng ký" class="btn" />
            </form>
        </div>
    </div>

    <div class="panels-container">
        <div class="panel left-panel">
            <div class="content">
                <h3>Bạn chưa có tài khoản?</h3>
                <p>
                    Hãy nhấn nút đăng ký ở bên dưới để có trải nghiệm tốt hơn với trang web
                </p>
                <button class="btn transparent" id="sign-up-btn">
                    Đăng ký
                </button>
            </div>
            <div class="image-container">
                <img src="/static/auth/img/product-big-2 copy.png" class="image" alt=""/>
            </div>
        </div>
        <div class="panel right-panel">
            <div class="content">
                <h3>Bạn đã có tài khoản?</h3>
                <p>
                    Hãy nhấn nút đăng nhập với tài khoản đã có sẵn để đến với trang web của chúng tôi
                </p>
                <button class="btn transparent" id="sign-in-btn">
                    Đăng nhập
                </button>
            </div>
            <div class="image-container">
                <img src="/static/auth/img/a2 - Copy.png" class="image" alt="" />
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/static/auth/app.js"/>"></script>
<script src="<c:url value='/static/auth/js/sendDirection.js'/> " type="text/javascript"></script>

<script type="text/javascript">
    (function() {
        // Check if action is 'register' from the query string
        let urlParams = new URLSearchParams(window.location.search);
        let action = urlParams.get('action');

        // If the action is 'register', trigger the registration view
        if (action === 'register') {
            document.getElementById('sign-up-btn').click();
        }
    })();
</script>


</body>
</html>