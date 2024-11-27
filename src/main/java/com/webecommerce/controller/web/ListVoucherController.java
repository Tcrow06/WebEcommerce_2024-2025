package com.webecommerce.controller.web;

import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.dto.discount.ProductDiscountDTO;
import com.webecommerce.service.IBillDiscountService;
import com.webecommerce.service.IProductDiscountService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/danh-sach-ma-giam-gia"})
public class ListVoucherController extends HttpServlet {

    @Inject
    private IBillDiscountService billDiscountService;

    @Inject
    private IProductDiscountService productDiscountService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày phù hợp với JSP
            String currentDate = sdf.format(new Date());
            request.setAttribute("currentDate", currentDate);

            String productName = request.getParameter("productName");
            boolean searchMode = productName != null && !productName.trim().isEmpty();
            if (searchMode) {
                request.setAttribute("searchMode", true);
            } else {
                request.setAttribute("searchMode", false);
            }

            List<BillDiscountDTO> billDiscountDTOS = billDiscountService.getAllBillDiscount();
            request.setAttribute("billDiscountList", billDiscountDTOS);
            List<ProductDiscountDTO> productDiscountDTOS = productDiscountService.getAllProductDiscount();
            request.setAttribute("productDiscountList", productDiscountDTOS);
            List<ProductDiscountDTO> list = productDiscountService.findProductDiscountByProductName(productName);
            request.setAttribute("searchDiscount", list);

            String path = request.getServletPath();
            if (path.equals("/danh-sach-ma-giam-gia")) {
                request.getRequestDispatcher("/views/web/list-voucher.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Không thể thực hiện thao tác này.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
