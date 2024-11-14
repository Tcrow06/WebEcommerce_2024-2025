package com.webecommerce.controller.web;

import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.entity.other.AddressEntity;
import com.webecommerce.service.IAddressService;
import com.webecommerce.service.IOrderInfoService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/trang-chu/thong-tin-ca-nhan"})
public class AccountController extends HttpServlet {

    @Inject
    private IOrderInfoService orderInfoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderInfoEntity> addressList = orderInfoService.getAllOrderInfos();
        request.setAttribute("addressList", addressList);
        request.getRequestDispatcher("/views/web/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


    }


    // Hàm xử lý CRUD địa chỉ
    private void handleAddress(HttpServletRequest request, HttpServletResponse response,
                               String action) throws ServletException, IOException {

    }

    // Hàm lấy thông tin địa chỉ từ request gửi lên
    private AddressEntity getAddressRequest(HttpServletRequest request) {
        AddressEntity address = new AddressEntity();
        address.setCity(request.getParameter("city"));
        address.setDistrict(request.getParameter("district"));
        address.setCommune(request.getParameter("commune"));
        address.setConcrete(request.getParameter("concrete"));
        return address;
    }
}
