package com.webecommerce.controller.login;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webecommerce.constant.EnumRole;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.service.ICustomerService;
import com.webecommerce.service.ISocialAccountService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/three-party-login"})
public class ThreePartyLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ISocialAccountService socialAccountService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String path;
        if (code !=null){
            if(state!=null){
                String decodedState = URLDecoder.decode(state, StandardCharsets.UTF_8);
                JsonObject stateJson = JsonParser.parseString(decodedState).getAsJsonObject();
                String sendDirection = stateJson.has("send-direction")||
                        !stateJson.get("send-direction").getAsString().isEmpty() ? stateJson.get("send-direction").getAsString()
                        :"/trang-chu";
                String provider = stateJson.has("provider")? stateJson.get("provider").getAsString() : "unknown";
                CustomerRequest acc = null;
                if(provider.equals("google")){
                    String accessToken = GoogleLogin.getToken(code);
                    acc = GoogleLogin.getUserInfo(accessToken);
                }else{
                    System.out.println("Unknown provider: " + provider);
                }

                if(acc !=null){
                    handleUserLogin(acc, provider, sendDirection, request, response);
                    System.out.println(acc);
                    return;
                }else {
                    request.setAttribute("status", 401);
                    request.setAttribute("msg", "User does not exists");
                    path = "/views/auth/signing.jsp";
                }

            }else {
                System.out.println("Sate is null");
                response.sendRedirect(request.getContextPath() + "/dang-nhap");
                return;
            }
        }
        else {
            path = "/views/auth/signing.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);

    }
    public void handleUserLogin(CustomerRequest customerRequest, String provider, String sendDirection, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CustomerResponse existingUser;
        if (provider.equals("google")) {
            existingUser = socialAccountService.findByGgID(customerRequest.getGgID());
        } else {
            existingUser = socialAccountService.findByFbID(customerRequest.getFbID());
        }
        if (existingUser == null) {
            existingUser = socialAccountService.save(customerRequest);
        }
        //Neu da ton tai thi tien hanh update
//        else {
//            existingUser = new CustomerResponse();
////            userModel.setId(existingUser.getId());
////            userModel = customerService.update(userModel);
//        }
        response.setContentType("application/json");
        String jwtToken = JWTUtil.generateToken(existingUser, EnumRole.CUSTOMER.toString());

        System.out.println("Generated JWT Token: " + jwtToken);

        Cookie cookie = new Cookie("token", jwtToken);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath()+ "/trang-chu");
//        response.sendRedirect(request.getContextPath() + "/" + sendDirection);

    }
    public void handleUserLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JWTUtil.destroyToken(request, response);
        response.sendRedirect(request.getContextPath() + "/dang-nhap");
    }
}
