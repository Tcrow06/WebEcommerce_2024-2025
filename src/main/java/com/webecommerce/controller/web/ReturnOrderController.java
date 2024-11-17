package com.webecommerce.controller.web;

import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDetailDTO;
import com.webecommerce.service.IOrderDetailService;
import com.webecommerce.service.IProductDiscountService;
import com.webecommerce.service.IProductVariantService;
import com.webecommerce.service.impl.OrderDetailService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/trang-chu/don-hang/danh-sach-don-hang/tra-san-pham"})
public class ReturnOrderController extends HttpServlet {
    @Inject
    private IOrderDetailService orderDetailService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedItems = request.getParameterValues("selectedItems");

        List<DisplayOrderDetailDTO> selectedOrderItems = new ArrayList<>();

        if (selectedItems != null) {
            for (String idStr : selectedItems) {
                Long id = Long.parseLong(idStr);
                DisplayOrderDetailDTO dto = orderDetailService.findOrderDetail(id);
                if(dto != null) {
                    selectedOrderItems.add(dto);
                }
            }
        }
        request.setAttribute("productList", selectedOrderItems);
        request.getRequestDispatcher("/views/web/return-order.jsp").forward(request, response);
    }
}
