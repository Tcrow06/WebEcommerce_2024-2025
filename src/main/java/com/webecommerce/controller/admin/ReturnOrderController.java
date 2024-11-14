package com.webecommerce.controller.admin;

import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.service.IReturnOrderService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/danh-sach-tra"})
public class ReturnOrderController extends HttpServlet {
    @Inject
    private IReturnOrderService returnOrderService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TransferListDTO> listDTOList = returnOrderService.getData();
        request.setAttribute("lstProductReturn", listDTOList);
        request.getRequestDispatcher("/views/admin/transfer/transfer-list.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //request.getRequestDispatcher("/views/admin/transfer/transfer-list.jsp").forward(request,response);
    }
}
