package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.service.IProductService;
import com.webecommerce.service.IProductVariantService;
import com.webecommerce.service.impl.ImageServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api-product"})
@MultipartConfig
public class ProductAPI extends HttpServlet {
    @Inject
    IProductService productService;

    @Inject
    IProductVariantService productVariantService;

    @Inject
    private ImageServiceImpl imageService;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json; charset=UTF-8"); // Thiết lập kiểu nội dung và mã hóa
        resp.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa UTF-8 cho phản hồi

        Map<String, Object> responseMap = new HashMap<>();
        try {
            Long idProduct = Long.valueOf(req.getParameter("id"));
            String color = req.getParameter("color");
            String size = req.getParameter("size");
            String atributenName = req.getParameter("atributeName");

            if (color != null && size != null) {
                ProductVariantDTO productVariant = productVariantService.getProductVariantByColorAndSize(idProduct, color, size);
                responseMap.put("productVariant", productVariant);
            }

            List<String> colorOrSizeAvaiable;
            if (atributenName.equals("size"))
                colorOrSizeAvaiable = productService.getListColorBySize(size, idProduct);
            else colorOrSizeAvaiable = productService.getListSizeByColor(color, idProduct);

            responseMap.put("colorOrSizeAvailable", colorOrSizeAvaiable);

            resp.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(resp.getWriter(), responseMap);

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        ProductDTO product = getPostInfo(req);
        try {
            if (product != null) {
                product = productService.save(product);
                if (product != null) {
                    mapper.writeValue(resp.getWriter(), product);
                } else mapper.writeValue(resp.getWriter(), "error");
            }
            System.out.println(product);
        } catch (Exception e) {
            mapper.writeValue(resp.getWriter(), "error");
        }
    }
    private ProductDTO getPostInfo(HttpServletRequest request) {
        Map<String, Object> formData = new HashMap<>();
        File rez = null;

        try {
            for (Part part : request.getParts()) {
                if (part.getContentType() == null) {
                    String fieldName = part.getName();
                    String fieldValue = new BufferedReader(new InputStreamReader(part.getInputStream()))
                            .lines().collect(Collectors.joining("\n"));
                    formData.put(fieldName, fieldValue);
                } else {
                    if (part.getName().equals("image")) {
                        try {
                            String realPath= getServletContext().getRealPath("/");
                            imageService.setRealPath(realPath);
                            imageService.setPath(part);
                            imageService.saveImageToDisk();
                            rez = imageService.getFile();

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        ProductDTO productDTO = new ObjectMapper().convertValue(formData, ProductDTO.class);
        if (productDTO.getId() != null) {
            productDTO = productService.getProductById(productDTO.getId());
            if (rez != null) {
                if (!imageService.delete(productDTO.getPhoto())) {
                    System.out.println("Delete image unsuccessfully");
                }
            }
        }


        // Cập nhật đường dẫn ảnh và ảnh thumbnail
        if (rez != null) {
            productDTO.setPhoto(rez.getName());
        }

        return productDTO;
    }
}
