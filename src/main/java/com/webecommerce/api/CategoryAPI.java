package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.service.ICategoryService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-category"})
public class CategoryAPI extends HttpServlet {
    @Inject
    ICategoryService categoryService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpUtils httpUtils = HttpUtils.of(req.getReader());
            CategoryDTO category = httpUtils.toModel(CategoryDTO.class);

            if (category != null) {
                category = categoryService.save(category);
                if (category != null) {
                    mapper.writeValue(resp.getWriter(), "Thêm category thành công");
                } else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
                    mapper.writeValue(resp.getWriter(), "Thêm category thất bại");
                }
            } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 500
        } catch (EntityExistsException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            mapper.writeValue(resp.getWriter(),  e.getMessage());
        }
        catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            mapper.writeValue(resp.getWriter(),  e.getMessage());
        }
    }

}
