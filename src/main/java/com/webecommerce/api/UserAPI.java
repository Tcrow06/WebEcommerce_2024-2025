package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.service.IAccountService;
import com.webecommerce.service.ICustomerService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/update-user"})
public class UserAPI extends HttpServlet {

    @Inject
    ICustomerService customerService;

    @Inject
    IAccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
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

            String otp = jsonObject.get("otp").getAsString();
            JsonObject userJson = jsonObject.getAsJsonObject("user");
            String name = userJson.get("name").getAsString();
            String email = userJson.get("email").getAsString();
            String phone = userJson.get("phone").getAsString();
            String id = userJson.get("id").getAsString();

            JsonObject accountJson = jsonObject.getAsJsonObject("account");
            String pass = accountJson.get("pass").getAsString();
            if (!pass.trim().isEmpty()) {
                long userId = Long.parseLong(id.trim());
                int otpCheck = accountService.verifyOTP(id, otp);
                if (otpCheck != 0) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("{\"error\": \"Mã OTP không chính xác.\"}");
                    return;
                }
                accountService.setPassword(userId, pass.trim());
            }

            String updateResult = customerService.updateInforCustomer(id, name, email, phone);
            if ("oke".equals(updateResult)) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"message\": \"Cập nhật thành công!\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Đã xảy ra lỗi khi cập nhật thông tin.\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Có lỗi xảy ra, vui lòng thử lại.\"}");
        }
    }

}
