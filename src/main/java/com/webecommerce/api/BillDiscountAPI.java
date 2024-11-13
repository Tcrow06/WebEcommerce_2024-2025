package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.service.impl.BillDiscountService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api-bill-discount")
public class BillDiscountAPI extends HttpServlet {

    @Inject
    private BillDiscountService billDiscountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        HttpUtils httpUtils =  HttpUtils.of(req.getReader()) ;
        BillDiscountDTO billDiscount = httpUtils.toModel(BillDiscountDTO.class);

        if (billDiscount != null) {
            billDiscount = billDiscountService.save(billDiscount) ;
            if (billDiscount != null) {
                mapper.writeValue(resp.getWriter(), billDiscountService.findAll());
            } else mapper.writeValue(resp.getWriter(), "error");
        }
    }

}
