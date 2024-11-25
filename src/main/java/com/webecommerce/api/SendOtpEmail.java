package com.webecommerce.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.webecommerce.service.IAccountService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/send-otp"})
public class SendOtpEmail extends HttpServlet {
    @Inject
    IAccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");

        try {
            StringBuilder requestBody = new StringBuilder();
            String line;
            try (BufferedReader reader = req.getReader()) {
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }
            }
            String jsonData = requestBody.toString();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);

            String email = jsonObject.get("email").getAsString();
            long userId = jsonObject.get("id").getAsLong();
            boolean isOtpSent = accountService.sendOTPToEmail(email, userId, "update");
            if (isOtpSent) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"message\": \"OTP đã được gửi.\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Không thể gửi OTP, vui lòng thử lại.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Có lỗi xảy ra, vui lòng thử lại.\"}");
        }
    }
}
