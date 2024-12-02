package com.webecommerce.controller.admin.product;
import com.webecommerce.constant.ModelConstant;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.response.admin.ProductVariantColorDTO;
import com.webecommerce.service.ICategoryService;
import com.webecommerce.service.IProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/chu-cua-hang/them-san-pham", "/chu-cua-hang/danh-sach-san-pham", "/chu-cua-hang/chinh-sua-san-pham"})
public class ProductController extends HttpServlet {
    @Inject
    ICategoryService categoryService;

    @Inject
    IProductService productService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/chu-cua-hang/them-san-pham")) {
            addProduct(request, response);
        } else if (action.equals("/chu-cua-hang/danh-sach-san-pham")) {
            productList(request, response);
        } else if (action.equals("/chu-cua-hang/chinh-sua-san-pham")) {
            updateProduct(request,response);
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(ModelConstant.MODEL, categoryService.findAll());
        request.getRequestDispatcher("/views/admin/product/add-product.jsp").forward(request, response);
    }

    private void updateProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(request.getParameter("id"));

            request.setAttribute(ModelConstant.CATEGORY, categoryService.findAll());

            ProductDTO productDTO = productService.getProductById(id);

            request.setAttribute( ModelConstant.MODEL,productDTO);


            request.getRequestDispatcher("/views/admin/product/edit-product.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            productList(request,response);
        }
    }

    private void productList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        String category = request.getParameter("category");
        String name = request.getParameter("name");

        if (category != null) {
            if (category.equals("all") || category.equals("")) {
                category = null;
            }
        }

        List <ProductDTO> productDTOS = null ;

        if (type != null) {
            if (type.equals("ngung-kinh-doanh"))
                productDTOS = productService.findProductStopSellingByCategoryAndName(category,name);
        }

        if (productDTOS == null) {
            productDTOS = productService.findProductSellingByCategoryAndName(category,name);
            type = "dang-kinh-doanh";
        }

        request.setAttribute(ModelConstant.TYPE_DISCOUNT,type);
        request.setAttribute(ModelConstant.CATEGORY, categoryService.findAll());
        request.setAttribute(ModelConstant.MODEL, productDTOS);
        request.getRequestDispatcher("/views/admin/product/product-list.jsp").forward(request, response);
    }
}
