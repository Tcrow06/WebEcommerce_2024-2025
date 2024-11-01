package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-product"})
public class UserAPI extends HttpServlet {

    @Inject
    ICustomerDAO customerDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");


        HttpUtils httpUtils =  HttpUtils.of(req.getReader()) ;
        CustomerEntity customer = httpUtils.toModel(CustomerEntity.class);
        if (customer != null) {
            if (customerDAO.delete(customer.getId())) {
                mapper.writeValue(resp.getOutputStream(), customer);
            } else mapper.writeValue(resp.getOutputStream(), "error");
        }
    }

}
