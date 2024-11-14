package com.webecommerce.utils;

import java.security.SecureRandom;

public class RandomUtils {
    public static int generateSixDigit() {
        SecureRandom rng = new SecureRandom();
        int otp = 100000 + rng.nextInt(900000); // Tạo số ngẫu nhiên 6 chữ số từ 100000 đến 999999
        return otp;
    }
}
