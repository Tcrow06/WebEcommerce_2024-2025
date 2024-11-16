package com.webecommerce.controller.admin.discount;

import com.webecommerce.constant.ModelConstant;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.service.IBillDiscountService;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/giam-gia-cho-don-hang","/chu-cua-hang/tao-giam-gia-cho-don-hang","/chu-cua-hang/chinh-sua-giam-gia-cho-don-hang"})
public class BillDiscountController extends HttpServlet {
    @Inject
    IBillDiscountService billDiscountService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/chu-cua-hang/giam-gia-cho-don-hang")) {
           billDiscountList(request,response);
        } else if (action.equals("/chu-cua-hang/tao-giam-gia-cho-don-hang")) {
            billDiscount(request,response);
        } else if (action.equals("/chu-cua-hang/chinh-sua-giam-gia-cho-don-hang")) {
            updateBillDiscount(request,response);
        }
    }

    private void billDiscount (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/discount/add-bill-discount.jsp").forward(request, response);
    }

    private void updateBillDiscount (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BillDiscountDTO billDiscountDTO = null;
            Long id = Long.valueOf(request.getParameter("id"));

            if (id != null) {
                billDiscountDTO = billDiscountService.findById(id);
                if (billDiscountDTO != null) {
                    request.setAttribute(ModelConstant.MODEL, billDiscountDTO);
                    request.getRequestDispatcher("/views/admin/discount/add-bill-discount.jsp").forward(request, response);
                }
                else billDiscountList(request,response);
            }
    }

    private void billDiscountList (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("type");
        List<BillDiscountDTO> billDiscountDTOS = null;
        String type = "dang-dien-ra";

        if (action != null) {
            if (action.equals("sap-dien-ra")) {
                billDiscountDTOS = billDiscountService.findBillDiscountUpComming();
                type = "sap-dien-ra";
            }
            else if (action.equals("da-ket-thuc")) {
                billDiscountDTOS = billDiscountService.findExpiredBillDiscount();
                type = "da-ket-thuc";
            }
        }

        if (billDiscountDTOS == null)
            billDiscountDTOS = billDiscountService.findBillDiscountValid();

        request.setAttribute(ModelConstant.TYPE_DISCOUNT, type);
        request.setAttribute(ModelConstant.MODEL, billDiscountDTOS);
        request.getRequestDispatcher("/views/admin/discount/bill-discount-list.jsp").forward(request, response);
    }

}
