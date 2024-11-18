package com.webecommerce.controller.admin;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.order.IOrderStatusDAO;
import com.webecommerce.dto.notinentity.TransferListDTO;
import com.webecommerce.service.IOrderStatusService;
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
        String action = request.getParameter("action");
        String orderDetailReturnId = request.getParameter("orderDetailReturnId");
        Long orderDetailId = -1L;
        if(orderDetailReturnId != null) {
            orderDetailId = Long.valueOf(orderDetailReturnId);
        }
        //ACTION TRA HANG
        if("return".equals(action)) {
            //Cập nhật trạng thái order detail là 1 và tăng lại lượng sản phẩm
            boolean updateStatusOrderDetail = returnOrderService.updateStatusOrder(orderDetailId);
        }
        else if("noReturn".equals(action)) { //ACTION KHONG CHO TRA
            // Cập nhật trạng thái order detail là 2
            boolean updateStatusOrderDetail = returnOrderService.updateStatusNoReturn(orderDetailId);
        }

        //Thay đổi trạng thái order nếu tất cả đơn hàng được trả
        boolean updateStatusReturn = returnOrderService.updateStatus(orderDetailId);

        if(!updateStatusReturn) {
            //Thay đổi trạng thái đơn hàng nếu tất cả được xử lý
            boolean updateStatusProcess = returnOrderService.updateStatusProcess(orderDetailId);
        }
        List<TransferListDTO> listDTOList = returnOrderService.getData();
        request.setAttribute("lstProductReturn", listDTOList);
        request.getRequestDispatcher("/views/admin/transfer/transfer-list.jsp").forward(request,response);
    }
}
