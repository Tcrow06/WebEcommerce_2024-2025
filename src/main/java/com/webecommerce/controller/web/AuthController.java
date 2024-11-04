package com.webecommerce.controller.web;

import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.exception.DuplicateFieldException;
import com.webecommerce.service.IAccountService;
import com.webecommerce.utils.FormUtils;
import com.webecommerce.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/dang-nhap", "/dang-ky"})
public class AuthController extends HttpServlet {
    @Inject
    private IAccountService accountService;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && (action.equals("login") || (action.equals("register")))) {
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");
            if (message != null && alert != null) {
                request.setAttribute("message", resourceBundle.getString(message));
                request.setAttribute("alert", alert);
            }
        }
        request.getRequestDispatcher("/decorators/auth.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if(action != null && action.equals("login")) {
            AccountRequest account = FormUtils.toModel(AccountRequest.class, request);
            account = accountService.findByUserNameAndPasswordAndStatus(account.getUserName(), account.getPassword(), "ACTIVE");
            if(account != null) {
                SessionUtil.getInstance().putValue(request, "ACCOUNTDTO", account);
                if(account.getRole().equals("OWNER")) {
                    response.sendRedirect(request.getContextPath() + "/chu-doanh-nghiep");
                }
                else if(account.getRole().equals("CUSTOMER")) {
                    response.sendRedirect(request.getContextPath() + "/trang-chu");
                }
            }
            else {
                session.setAttribute("loginData", account);
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
            }
        }
        else if(action != null && action.equals("register")) {
            CustomerRequest customerRequest = FormUtils.toModel(CustomerRequest.class, request);
            try {
                CustomerResponse customerResponse = accountService.save(customerRequest);
                if (customerResponse != null) {
                    response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=register_success&alert=success");
                }
            } catch (DuplicateFieldException e) {
                session.setAttribute("registrationData", customerRequest);
                String errorMessage;
                switch (e.getFieldName()) {
                    case "email":
                        errorMessage = "duplicate_email";
                        break;
                    case "phone":
                        errorMessage = "duplicate_phone";
                        break;
                    case "username":
                        errorMessage = "duplicate_username";
                        break;
                    default:
                        errorMessage = "duplicate_information";
                        break;
                }
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=register&message=" + errorMessage + "&alert=danger");
            }
        }
    }
}
