package com.webecommerce.controller.login;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.CartItemDTO;
import com.webecommerce.dto.request.people.CustomerRequest;
import com.webecommerce.dto.response.people.CustomerResponse;
import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.cart.CartItemEntity;
import com.webecommerce.mapper.Impl.CartItemMapper;
import com.webecommerce.service.ICartItemService;
import com.webecommerce.service.ISocialAccountService;
import com.webecommerce.service.impl.CustomerService;
import com.webecommerce.utils.JWTUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/three-party-login","/dang-xuat"})
public class ThreePartyLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ISocialAccountService socialAccountService;

    @Inject
    private CustomerService customerService;

    @Inject
    private  ICartItemService cartItemService;
    private ICustomerDAO customerDAO;

    @Inject
    private CartItemMapper cartItemMapper;

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
                }else if(provider.equals("facebook")){
                    String accessToken = FacebookLogin.getToken(code);
                    acc = FacebookLogin.getUserInfo(accessToken);
                }
                else{
                    System.out.println("Unknown provider: " + provider);
                    response.sendRedirect(request.getContextPath() + "/dang-nhap?message=khong_hop_le&alert=danger");
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
                response.sendRedirect(request.getContextPath() + "/dang-nhap?message=loi_server&alert=warning");
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
        HashMap<Long, CartItemDTO> cart = new HashMap<>();
        HttpSession session = request.getSession();
        CustomerResponse existingUser;
        if (provider.equals("google")) {
            //Chưa chỉnh sửa nhất quan về thông tin đăng nhập bằng gg
            existingUser = socialAccountService.findByGgID(customerRequest.getGgID());
            if(existingUser==null){
                existingUser = socialAccountService.saveSocialByEmail(customerRequest);
            }
        } else {
            existingUser = socialAccountService.findByFbID(customerRequest.getFbID());
        }
        if (existingUser == null) {
            existingUser = socialAccountService.save(customerRequest);
        }

        existingUser.setRole("CUSTOMER");
        cart=cartItemService.LoadCart(JWTUtil.getIdUser(request));
        request.getSession().setAttribute("cart", cart);


        response.setContentType("application/json");
        String jwtToken = JWTUtil.generateToken(existingUser);

        System.out.println("Generated JWT Token: " + jwtToken);

        Cookie cookie = new Cookie("token", jwtToken);

        try{
            CartEntity cartEntity = customerDAO.findById(existingUser.getId()).getCart();

            for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
                CartItemDTO cartItemDTO = cartItemMapper.toDTO(cartItemEntity);
                cart.put(cartItemDTO.getId(), cartItemDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        request.getSession().setAttribute("cart", cart);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath()+ "/trang-chu");
//        response.sendRedirect(request.getContextPath() + "/" + sendDirection);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JWTUtil.destroyToken(request, response);
        request.getSession().removeAttribute("cart");
        response.sendRedirect(request.getContextPath() + "/trang-chu");
    }

    public void handleUserLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JWTUtil.destroyToken(request, response);
        response.sendRedirect(request.getContextPath() + "/dang-nhap");
    }

}
