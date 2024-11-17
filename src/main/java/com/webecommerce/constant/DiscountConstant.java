package com.webecommerce.constant;

public class DiscountConstant {
    public static final String UPCOMING = "sắp diễn ra";
    public static final String VALID = "đang diễn ra";
    public static final String EXPIRED = "đã kết thúc";

    public static String getClassBootstrap (String value) {
        if (value.equals(UPCOMING)) return "danger";
        else if (value.equals(VALID)) return "success";
        else if (value.equals(EXPIRED)) return "secondary";

        return "";
    }
}


