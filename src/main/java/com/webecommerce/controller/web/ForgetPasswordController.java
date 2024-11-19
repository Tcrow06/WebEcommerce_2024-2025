package com.webecommerce.controller.web;

import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.service.IAccountService;
import com.webecommerce.service.ICustomerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/quen-mat-khau"})
public class ForgetPasswordController extends HttpServlet {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    @Inject
    private IAccountService accountService;

    @Inject
    private ICustomerService customerService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String message = request.getParameter("message");
        String alert = request.getParameter("alert");
        if (message != null && alert != null) {
            request.setAttribute("message", resourceBundle.getString(message));
            request.setAttribute("alert", alert);
        }
        if (action != null && action.equals("verify")) {

            request.getRequestDispatcher("/views/web/enter-OTP.jsp").forward(request,response);
        } else if(action != null && action.equals("set_password")) {
            request.getRequestDispatcher("/views/web/confirm-password.jsp").forward(request,response);
        }

        request.getRequestDispatcher("/views/web/forget-password.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action != null && action.equals("verify")) {
            String otp = request.getParameter("otp");
            String id = request.getParameter("id");
            int count = accountService.verifyOTP(id, otp);
            if (count == 0) {
                response.sendRedirect(request.getContextPath() + "/quen-mat-khau?action=set_password&id=" + id + "&message=verify_success&alert=success");
            }else  if (count == 5) {
                response.sendRedirect(request.getContextPath() + "/dang-ky?action=verify&id=" + id +"&message=verify_failed&alert=danger");
            }
            else {
                response.sendRedirect(request.getContextPath() + "/dang-ky?action=verify&id=" + id +"&message=verify_retry&alert=danger");
            }
        } else if (action != null && action.equals("set_password")) {
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            if (!password.equals(repassword)) {
                response.sendRedirect(request.getContextPath() + "/quen-mat-khau?action=set_password&id=" + id + "&message=not_match_password&alert=danger");
            } else {
                accountService.setPassword(Long.parseLong(id), password);
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=verify_success&alert=success");
            }
        }
        else {
            String email = request.getParameter("email");
            String username = request.getParameter("username");

            boolean exists = accountService.existsUsernameAndEmail(username, email);
            if (!exists) {
                response.sendRedirect(request.getContextPath() + "/quen-mat-khau?message=not_found_user&alert=danger");
            } else {
                CustomerResponse customerResponse = customerService.findByEmail(email);
                boolean ok = accountService.sendOTPToEmail(customerResponse.getEmail(), customerResponse.getId(), "forget-password");
                if (ok) {
                    response.sendRedirect(request.getContextPath() + "/quen-mat-khau?action=verify&id=" + customerResponse.getId());
                } else {
                    response.sendRedirect(request.getContextPath() + "/quen-mat-khau?message=" + "&alert=danger");
                }
            }
        }


    }
}
