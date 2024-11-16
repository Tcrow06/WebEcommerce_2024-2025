package com.webecommerce.controller.web;

import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.service.IProductDiscountService;
import com.webecommerce.service.IProductVariantService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/tra-san-pham"})
public class ReturnOrderController extends HttpServlet {
    @Inject
    private IProductDiscountService productDiscountService;
    @Inject
    private IProductVariantService productVariantService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher("/views/web/return-order.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedItems = request.getParameterValues("orderitems");

        List<OrderDetailDTO> selectedOrderItems = new ArrayList<>();

        if (selectedItems != null) {
            for (String itemId : selectedItems) {
                String quantity = request.getParameter("quantity-" + itemId);
                String productDiscountId = request.getParameter("productDiscount-" + itemId);
                String productVariantId = request.getParameter("productVariant-" + itemId);

                OrderDetailDTO orderItem = new OrderDetailDTO();
                orderItem.setId(Long.valueOf(itemId));
                orderItem.setQuantity(Integer.parseInt(quantity));
                orderItem.setProductDiscount(productDiscountService.findById(Long.valueOf(productDiscountId)));
                orderItem.setProductVariant(productVariantService.findById(Long.valueOf(productVariantId)));
                selectedOrderItems.add(orderItem);
            }
        }

        request.setAttribute("productList", selectedOrderItems);

        request.getRequestDispatcher("/views/web/return-order.jsp").forward(request, response);
    }
}
