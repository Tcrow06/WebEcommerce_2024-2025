package com.webecommerce.controller.web;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.dto.request.other.AccountRequest;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.dto.response.people.UserResponse;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.exception.DuplicateFieldException;
import com.webecommerce.mapper.Impl.CartItemMapper;
import com.webecommerce.service.IAccountService;
import com.webecommerce.service.ICartItemService;
import com.webecommerce.service.impl.CartItemService;
import com.webecommerce.service.ICacheService;
import com.webecommerce.utils.CacheFactory;
import com.webecommerce.utils.FormUtils;
import com.webecommerce.utils.JWTUtil;
import com.webecommerce.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/dang-nhap", "/dang-ky"})
public class AuthController extends HttpServlet {
    @Inject
    private IAccountService accountService;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Inject
    private ICustomerDAO customerDAO;

    @Inject
    private CartItemMapper cartItemMapper;
    @Inject
    private ICartItemService cartItemService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String send_direction = request.getParameter("send-direction");
        if(send_direction!=null){
            session.setAttribute("send-direction",send_direction);
        }


        String action = request.getParameter("action");
        if (action != null && (action.equals("login") || (action.equals("register")))) {
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");
            if (message != null && alert != null) {
                request.setAttribute("message", resourceBundle.getString(message));
                request.setAttribute("alert", alert);
            }
            request.getRequestDispatcher("/decorators/auth.jsp").forward(request, response);
        } else if (action != null && (action.equals("verify"))) {
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");
            if (message != null && alert != null) {
                request.setAttribute("message", resourceBundle.getString(message));
                request.setAttribute("alert", alert);
            }
            request.getRequestDispatcher("/views/web/enter-OTP.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/decorators/auth.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        CheckOutRequestDTO checkOutRequestDTO =(CheckOutRequestDTO) session.getAttribute("orderNotHandler");
        if(action != null && action.equals("login")) {
            AccountRequest account = FormUtils.toModel(AccountRequest.class, request);
            UserResponse foundUser = accountService.findByUserNameAndPasswordAndStatus(account.getUserName(), account.getPassword(), "UNVERIFIED");
            if (foundUser != null) {
                accountService.sendOTPToEmail(foundUser.getEmail(), foundUser.getId(), "register");
                response.sendRedirect(request.getContextPath() + "/dang-ky?action=verify&id=" + foundUser.getId() + "&message=unverified&alert=danger");
                return;
            }

            UserResponse user = accountService.findByUserNameAndPasswordAndStatus(account.getUserName(), account.getPassword(), "ACTIVE");

            if(user != null) {
                response.setContentType("application/json");
                String path=null,jwtToken=null;

                SessionUtil.getInstance().putValue(request, "USERINFO", user);
                if(user.getRole().equals("OWNER")) {
                    jwtToken = JWTUtil.generateToken(user);
                    path = "/chu-doanh-nghiep";
                }
                else if(user.getRole().equals("CUSTOMER")) {
                    HashMap<Long, CartItemDTO> cart = new HashMap<>();
                    if(checkOutRequestDTO!=null){
                        cart = cartItemService.updateCartWhenBuy(user.getId(),checkOutRequestDTO);
                    }else{
                        cart=cartItemService.LoadCart(JWTUtil.getIdUser(request));
                    }


                    request.getSession().setAttribute("cart", cart);
                    jwtToken = JWTUtil.generateToken(user);
                    path="/trang-chu";
                    if(session.getAttribute("send-direction")!=null){

                        path = session.getAttribute("send-direction").toString();
                        session.removeAttribute("send-direction");
                    }

                }
                System.out.println("Generated JWT Token: " + jwtToken);

                Cookie cookie = new Cookie("token", jwtToken);
                response.addCookie(cookie);

                response.sendRedirect(request.getContextPath() + path);
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
                    // Send otp to email with expiration time in 3 minutes
                    boolean ok = accountService.sendOTPToEmail(customerResponse.getEmail(), customerResponse.getId(), "register");
                    if (ok) {
                        response.sendRedirect(request.getContextPath() + "/dang-ky?action=verify&id=" + customerResponse.getId());
                    } else {
                        response.sendRedirect(request.getContextPath() + "/dang-nhap?action=register&message=" + "&alert=danger");
                    }
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
        } else if (action != null && action.equals("verify")) {
            String otp = request.getParameter("otp");
            String id = request.getParameter("id");
            int count = accountService.verifyOTP(id, otp);
            if (count == 0) {
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=verify_success&alert=success");
            }else  if (count == 5) {
                response.sendRedirect(request.getContextPath() + "/dang-ky?action=verify&id=" + id +"&message=verify_failed&alert=danger");
            }
            else {
                response.sendRedirect(request.getContextPath() + "/dang-ky?action=verify&id=" + id +"&message=verify_retry&alert=danger");
            }
        }
    }
}
