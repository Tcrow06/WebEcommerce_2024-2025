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
    <link rel="stylesheet" href="<c:url value="/static/auth/style.css"/>" />
    <title>Sign in & Sign up Form</title>
</head>
<body>
<div class="container">
    <div class="forms-container">
        <div class="signin-signup">
            <form action="#" class="sign-in-form">
                <h2 class="title">Sign in</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Username" />
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Password" />
                </div>
                <input type="submit" value="Login" class="btn solid" />
                <p class="social-text">Or Sign in with social</p>
                <div class="social-media">
                    <a href="#" class="social-icon">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a id="google-id" href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/three-party-login&response_type=code&client_id=166848038588-5bre3evlsm652tcp88lrogu6m189s5lb.apps.googleusercontent.com&approval_prompt=force" class="social-icon">
                        <i class="fab fa-google"></i>
                    </a>
                </div>
            </form>
            <form action="#" class="sign-up-form">
                <h2 class="title">Sign up</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Username" />
                </div>
                <div class="input-field">
                    <i class="fas fa-envelope"></i>
                    <input type="email" placeholder="Email" />
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Password" />
                </div>
                <input type="submit" class="btn" value="Sign up" />
            </form>
        </div>
    </div>

    <div class="panels-container">
        <div class="panel left-panel">
            <div class="content">
                <h3>Don't you have account?</h3>
                <p>
                    Please register now to browse and order great books on our website
                </p>
                <button class="btn transparent" id="sign-up-btn">
                    Sign up
                </button>
            </div>
            <img src="<c:url value="/static/auth/img/signin.png"/>" class="image" alt="" />
        </div>
        <div class="panel right-panel">
            <div class="content">
                <h3>Create your account</h3>
                <p>
                    Create an account to access our exclusive book collection and enjoy seamless ordering.
                </p>
                <button class="btn transparent" id="sign-in-btn">
                    Sign in
                </button>
            </div>
            <img src="<c:url value="/static/auth/img/signup.png"/>" class="image" alt="" />
        </div>
    </div>
</div>

<script src="<c:url value="/static/auth/app.js"/>"></script>
<script src="<c:url value='/static/auth/js/sendDirection.js'/> " type="text/javascript"></script>
</body>
</html>