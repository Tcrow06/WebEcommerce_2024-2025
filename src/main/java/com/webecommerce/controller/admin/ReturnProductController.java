package com.webecommerce.controller.admin;

import com.webecommerce.dto.notinentity.ProductReturnDTO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.service.IReturnOrderService;
import com.webecommerce.service.impl.ReturnOrderService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/danh-sach-tra/tra-san-pham"})
public class ReturnProductController extends HttpServlet {
    @Inject
    private IReturnOrderService returnOrderService ;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String returnOrderIdStr = request.getParameter("id");
        Long returnOrderId = -1L;
        if(returnOrderIdStr != null) {
            returnOrderId = Long.valueOf((returnOrderIdStr));
        }
        CustomerResponse customer = returnOrderService.getCustomerData(returnOrderId);
        ProductReturnDTO productReturnDTO = returnOrderService.getProductReturnData(returnOrderId);
        request.setAttribute("customer", customer);
        request.setAttribute("productReturn", productReturnDTO);
        request.getRequestDispatcher("/views/admin/transfer/transfer-product.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
