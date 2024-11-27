package com.webecommerce.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.constant.ModelConstant;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.StatisticDTO;
import com.webecommerce.service.ICategoryService;
import com.webecommerce.service.IStatisticService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/chu-cua-hang","/thong-ke"})
public class HomeController extends HttpServlet {

    @Inject
    ICategoryService categoryService;

    @Inject
    private IStatisticService statisticService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonResponse = new HashMap<>();
        try {
            BufferedReader reader = request.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            Map<String, Object> requestData = objectMapper.readValue(json.toString(), Map.class);
            int year=Integer.parseInt(requestData.get("year").toString());

            List<Double> list = statisticService.calculateMonthlyRevenues(year);
            double sum = list.stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
            jsonResponse.put("monthlyRevenue", list);
            jsonResponse.put("revenue", sum);

        } catch (NumberFormatException e) {
            jsonResponse.put("error", "Invalid year format");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            jsonResponse.put("error", "An error occurred");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        objectMapper.writeValue(response.getWriter(), jsonResponse);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatisticDTO statisticDTO = statisticService.calculateHomeAdmin();
        try {
            request.setAttribute("statistic",statisticDTO);
            request.getRequestDispatcher("/views/admin/home.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}