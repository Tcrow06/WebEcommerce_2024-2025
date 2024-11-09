package com.webecommerce.controller.web;

import com.webecommerce.constant.ModelConstant;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.filter.FilterProduct;
import com.webecommerce.filter.FilterProductVariant;
import com.webecommerce.paging.PageRequest;
import com.webecommerce.paging.Pageable;
import com.webecommerce.service.ICategoryService;
import com.webecommerce.service.IProductService;
import com.webecommerce.service.IProductVariantService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/danh-sach-san-pham"})
public class ProductController extends HttpServlet {

    @Inject
    private IProductService productService;
    @Inject
    private ICategoryService categoryService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductDTO product = new ProductDTO();

        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        int page = Integer.parseInt(request.getParameter("page"));
        int maxPageItem = Integer.parseInt(request.getParameter("maxPageItem"));
        String minPriceStr = (request.getParameter("minPrice"));
        String maxPriceStr = request.getParameter("maxPrice");

        product.setPage(page);
        product.setMaxPageItem(maxPageItem);

        int categoryId = -1;

        try {
            categoryId = Integer.parseInt(category);
        }
        catch (NumberFormatException e) {
            System.out.println(e);
        }

        double minPrice = 0;
        double maxPrice = 9999;
        if(maxPriceStr != null && !maxPriceStr.isEmpty()) {
            maxPrice = Double.parseDouble(maxPriceStr);
        }
        if(minPriceStr != null && !minPriceStr.isEmpty()) {
            minPrice = Double.parseDouble(minPriceStr);
        }

        //product = FormUtils.toModel(ProductDTO.class, request);

        Pageable pageable =new PageRequest(product.getPage(), product.getMaxPageItem(), new FilterProduct(categoryId, brand));

        List<ProductDTO> productDTOList = productService.findAll(pageable, minPrice, maxPrice);
        System.out.println("AAAAAAAA" + productDTOList.size());
        product.setResultList(productDTOList);

        //product.setResultList(productService.findAll(pageable));
        product.setTotalItem(productService.getTotalItem());
        product.setTotalPage(productService.setTotalPage(product.getTotalItem(),
                product.getMaxPageItem()));

        request.setAttribute(ModelConstant.MODEL2, productService.getBrands());
        request.setAttribute(ModelConstant.MODEL1, categoryService.findAll());

        request.setAttribute(ModelConstant.MODEL,product);
        request.getRequestDispatcher("/views/web/product-list.jsp").forward(request, response);
    }
}
