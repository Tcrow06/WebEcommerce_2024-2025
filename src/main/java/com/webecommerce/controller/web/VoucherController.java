package com.webecommerce.controller.web;

import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.service.IBillDiscountService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = {"/phieu-giam-gia"})
public class VoucherController extends HttpServlet {
    @Inject
    private IBillDiscountService billDiscountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = JWTUtil.getIdUser(req);
        List<BillDiscountDTO> billDiscountDTOS = billDiscountService.getAllDiscountEligible(id);
        req.setAttribute("discountList", billDiscountDTOS);
        req.getRequestDispatcher("/views/web/voucher.jsp").forward(req, resp);
    }
}
