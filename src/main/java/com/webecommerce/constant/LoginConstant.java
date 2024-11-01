package com.webecommerce.constant;

public interface LoginConstant {
    public static final String GOOGLE_CLIENT_ID = "166848038588-5bre3evlsm652tcp88lrogu6m189s5lb.apps.googleusercontent.com";

    public static final String GOOGLE_REDIRECT_URI = "http://localhost:8080/three-party-login";

    public static final String GOOGLE_GRANT_TYPE = "authorization_code";

    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
}
