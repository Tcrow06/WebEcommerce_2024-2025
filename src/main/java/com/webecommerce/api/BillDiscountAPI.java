package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.service.impl.BillDiscountService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api-bill-discount")
public class BillDiscountAPI extends HttpServlet {

    @Inject
    private BillDiscountService billDiscountService;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");

        try {
            HttpUtils httpUtils =  HttpUtils.of(req.getReader()) ;
            BillDiscountDTO billDiscount = httpUtils.toModel(BillDiscountDTO.class);

            if (billDiscount != null) {
                if (billDiscount.getId() == null) {
                    addBillDiscount(req,resp,billDiscount);
                } else {
                    updateBillDiscount(req,resp,billDiscount);
                }
            }

        } catch (EntityExistsException entityExistsException) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            mapper.writeValue(resp.getWriter(),  entityExistsException.getMessage());
        }
        catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), "Lỗi xử lý: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");

        try {
            HttpUtils httpUtils =  HttpUtils.of(req.getReader()) ;
            BillDiscountDTO billDiscount = httpUtils.toModel(BillDiscountDTO.class);

            if (billDiscount != null) {
                cancelBillDiscount(req,resp,billDiscount);
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), "Lỗi xử lý: " + e.getMessage());
        }
    }

    private void addBillDiscount (HttpServletRequest req, HttpServletResponse resp, BillDiscountDTO billDiscount) throws ServletException, IOException {
        billDiscount = billDiscountService.save(billDiscount);
        if (billDiscount != null) {
            mapper.writeValue(resp.getWriter(), "Thêm chương trình giảm giá thành công!");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(),"Thêm chương trình thất bại !");
        }
    }


    private void updateBillDiscount (HttpServletRequest req, HttpServletResponse resp, BillDiscountDTO billDiscount) throws IOException {
        billDiscount = billDiscountService.update(billDiscount);
        if (billDiscount != null) {
            mapper.writeValue(resp.getWriter(), "Chỉnh sửa chương trình giảm giá thành công!");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), "Thêm chương trình thất bại !");
        }
    }

    private void cancelBillDiscount (HttpServletRequest req, HttpServletResponse resp, BillDiscountDTO billDiscount) throws IOException {
        billDiscount = billDiscountService.cancelProductDiscount(billDiscount.getId());
        if(billDiscount != null) {
            mapper.writeValue(resp.getWriter(), "Hủy chương trình giảm giá thành công !");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), "Hủy chương trình thất bại !");
        }
    }

}
