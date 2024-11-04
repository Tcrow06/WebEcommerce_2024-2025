package com.webecommerce.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    // Duy trì thông tin trong hệ thống của người dùng
    private static SessionUtil sessionUtil = null;
    public static SessionUtil getInstance() {
        if(sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }
    public void putValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}
