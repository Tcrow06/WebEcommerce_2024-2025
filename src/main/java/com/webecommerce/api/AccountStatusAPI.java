package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dto.notinentity.UpdateStatusAccountDTO;
import com.webecommerce.service.ICustomerService;
import com.webecommerce.service.impl.CustomerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-account-status"})
public class AccountStatusAPI extends HttpServlet {
    @Inject
    private ICustomerService customerService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // Gửi response về client
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            StringBuilder jsonBuider = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()){
                while((line = reader.readLine()) != null) {
                    jsonBuider.append(line);
                }
            }
            String json = jsonBuider.toString();

            UpdateStatusAccountDTO updateStatusAccountDTO = objectMapper.readValue(json, UpdateStatusAccountDTO.class);

            Long userId = updateStatusAccountDTO.getUserId();
            EnumAccountStatus status = updateStatusAccountDTO.getStatus();

            boolean updated = customerService.updateStatusAccount(userId, status);
            if(updated) {
                out.write(objectMapper.writeValueAsString(Map.of("success", true, "message", "Cập nhật trạng thái thành công")));
            }
            else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(objectMapper.writeValueAsString(Map.of("success", false, "message", "Cập nhật trạng thái thất bại!")));
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(response.getWriter(), "{\"error\": \"Server error: " + e.getMessage() + "\"}");
        }

    }
}
