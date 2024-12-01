<%@ page import="com.webecommerce.dto.request.people.CustomerRequest" %>
<%@ page import="com.webecommerce.dto.request.other.AccountRequest" %>
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
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/error/css/font-awesome.min.css"/>" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/static/auth/style.css"/>" />
    <title>Đăng nhập và đăng ký</title>
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">

            <%@ page session="true" %>
            <%
                AccountRequest account = (AccountRequest) session.getAttribute("loginData");
            %>
            <form action="<c:url value='/dang-nhap'/>" class="sign-in-form" method="post" id="loginForm">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}" role="alert" id="login-error-message">
                            ${message}
                    </div>
                </c:if>
                <c:if test="${not empty link}">
                    <div class="social-media">
                        <a href="https://mail.google.com/mail/?view=cm&fs=1&to=elevenfashionshop11@gmail.com&su=Support Needed&body=Xin chào, tôi cần hỗ trợ về..." target="_blank"><i class="fab fa-google"></i></a>
                    </div>
                </c:if>
                <label id="message_login" style="color: red"></label>
                <h2 class="title">Đăng nhập</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Tên đăng nhập" id="userName" name="userName"
                           value="<%= account != null ? account.getUserName() : "" %>"/>
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Mật khẩu" id="password" name="password" />
                </div>
                <div class="container-action">
                    <a href="<c:url value='/quen-mat-khau'/>" class="forgot-password" style="color: #bca58c;">Quên mật khẩu?</a>
                </div>
                <input type="hidden" name="action" value="login" />
                <input id="btn-login" type="button" value="Đăng nhập" class="btn solid" onclick="validateAndSubmit()"/>
                <p class="social-text">Hoặc đăng nhập bằng phương thức khác</p>
                <div class="social-media">
                    <a id="facebook-id" href="https://www.facebook.com/v20.0/dialog/oauth?client_id=1217837109270713&redirect_uri=http://localhost:8080/three-party-login&scope=email,public_profile" class="social-icon">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a id="google-id" href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/three-party-login&response_type=code&client_id=166848038588-5bre3evlsm652tcp88lrogu6m189s5lb.apps.googleusercontent.com&approval_prompt=force" class="social-icon">
                        <i class="fab fa-google"></i>
                    </a>
                </div>
            </form>

            <%@ page session="true" %>
            <%
                CustomerRequest registrationData = (CustomerRequest) session.getAttribute("registrationData");
            %>

            <form action="<c:url value='/dang-ky'/>" class="sign-up-form" method="post" id="form-register">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}" role="alert" id="register-error-message">
                            ${message}
                    </div>
                </c:if>

                <label id="message_register" style="color: red"></label>
                <h2 class="title">Tạo tài khoản</h2>

                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Họ và tên" id="name" name="name"
                           value="<%= registrationData != null ? registrationData.getName() : "" %>"/>
                </div>

                <div class="input-field">
                    <i class="fas fa-phone"></i>
                    <input type="tel" placeholder="Số điện thoại" pattern="[0-9]{10}" id="phone" name="phone"
                           value="<%= registrationData != null ? registrationData.getPhone() : "" %>"/>
                </div>

                <div class="input-field">
                    <i class="fas fa-envelope"></i>
                    <input type="email" placeholder="Email" id="email" name="email"
                           value="<%= registrationData != null ? registrationData.getEmail() : "" %>"/>
                </div>

                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Tên đăng nhập" name="userName" id = "user-regis"
                           value="<%= registrationData != null ? registrationData.getUserName() : "" %>"/>
                </div>

                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Mật khẩu" name="password" id = "pass-regis"/>
                </div>
                <input type="hidden" name="action" value="register" />
                <input type="button" value="Đăng ký" class="btn" onclick="validateAndSubmitRegister()"/>
            </form>
            <%
                String queryString = request.getQueryString();

                if (queryString != null && queryString.contains("message=register_success")) {
                    if (registrationData != null) {
                        session.removeAttribute("registrationData");
                    }
                }
            %>
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
<script>
    // Xóa tham số khỏi URL
    if (window.location.search) {
        const baseUrl = window.location.origin + window.location.pathname;
        history.replaceState(null, "", baseUrl);
    }

    function validateAndSubmitRegister() {
        const userName = document.getElementById('user-regis').value.trim();
        const name = document.getElementById('name').value.trim();
        const phone = document.getElementById('phone').value.trim();
        const pass = document.getElementById('pass-regis').value.trim();
        const email = document.getElementById('email').value.trim();
        const messageRegis = document.getElementById('message_register');

        // Kiểm tra giá trị của các trường nhập
        if(!name){
            messageRegis.textContent = "Vui lòng nhập tên.";
            return false;
        }
        if(!phone){
            messageRegis.textContent = "Vui lòng nhập số điện thoại.";
            return false;
        }
        if(!email){
            messageRegis.textContent = "Vui lòng nhập email.";
            return false;
        }
        if (!userName) {
            messageRegis.textContent = "Vui lòng nhập tên đăng nhập.";
            return false;
        }

        if (!pass) {
            messageRegis.textContent = "Vui lòng nhập mật khẩu.";
            return false;
        }

        // Nếu không có lỗi, submit form
        messageRegis.textContent = ""; // Xóa thông báo lỗi (nếu có)
        document.getElementById('form-register').submit();
    }
    function validateAndSubmit() {
        const userName = document.getElementById('userName').value.trim();
        const password = document.getElementById('password').value.trim();
        const messageLogin = document.getElementById('message_login');

        // Kiểm tra giá trị của các trường nhập
        if (!userName) {
            messageLogin.textContent = "Vui lòng nhập tên đăng nhập.";
            return false;
        }

        if (!password) {
            messageLogin.textContent = "Vui lòng nhập mật khẩu.";
            return false;
        }

        // Nếu không có lỗi, submit form
        messageLogin.textContent = ""; // Xóa thông báo lỗi (nếu có)
        document.getElementById('loginForm').submit();
    }
</script>
<script src="<c:url value='/static/auth/js/sendDirection.js'/> " type="text/javascript"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</body>
</html>