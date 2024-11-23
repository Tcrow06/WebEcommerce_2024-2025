package com.webecommerce.controller.admin;

import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.dto.notinentity.ManageUserDTO;
import com.webecommerce.service.ICustomerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/quan-ly-nguoi-dung"})
public class ManageUserController extends HttpServlet {
    @Inject
    private ICustomerService customerService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ManageUserDTO> users = customerService.getInfoUser();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/admin/user-list.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
