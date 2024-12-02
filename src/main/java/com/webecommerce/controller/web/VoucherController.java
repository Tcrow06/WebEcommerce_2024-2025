package com.webecommerce.controller.web;

import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.service.IBillDiscountService;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/tim-kiem-ma-giam-gia"})
public class VoucherController extends HttpServlet {

    @Inject
    private IBillDiscountService billDiscountService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập định dạng phản hồi là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Đọc dữ liệu JSON từ yêu cầu
            String jsonData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

            // Phân tích dữ liệu JSON để lấy mã giảm giá
            JSONObject jsonObject = new JSONObject(jsonData);
            String couponCode = jsonObject.optString("couponCode", "").trim();

            if (couponCode.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"message\": \"Mã giảm giá không được để trống.\"}");
                return;
            }

            // Tìm kiếm mã giảm giá trong cơ sở dữ liệu
            BillDiscountDTO searchDiscount = billDiscountService.findBillDiscountByCodeAndValid(couponCode);

            if (searchDiscount != null) {
                // Trả về thông tin mã giảm giá hợp lệ
                JSONObject result = new JSONObject();
                result.put("code", searchDiscount.getCode());
                result.put("name", searchDiscount.getName());
                result.put("minimumInvoiceAmount", searchDiscount.getMinimumInvoiceAmount());
                result.put("maximumAmount", searchDiscount.getMaximumAmount());
                result.put("discountPercentage", searchDiscount.getDiscountPercentage());

                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(result.toString());
            } else {
                // Trả về thông báo lỗi nếu mã giảm giá không hợp lệ hoặc hết hạn
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"message\": \"Mã giảm giá không hợp lệ hoặc đã hết hạn.\"}");
            }
        } catch (Exception e) {
            // Ghi log lỗi và trả về mã lỗi HTTP 500
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"Đã xảy ra lỗi trong quá trình xử lý.\"}");
        }
    }
}