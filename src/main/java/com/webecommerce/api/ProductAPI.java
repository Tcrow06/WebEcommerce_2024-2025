package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.service.IProductService;
import com.webecommerce.service.IProductVariantService;
import com.webecommerce.utils.HttpUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-product"})
@MultipartConfig
public class ProductAPI extends HttpServlet {
    @Inject
    IProductService productService;

    @Inject
    IProductVariantService productVariantService;

    @Override
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

    private ObjectMapper objectMapper = new ObjectMapper();

    private int getIndexFromParameter(String parameterName) {
        int startIndex = parameterName.indexOf('[') + 1;
        int endIndex = parameterName.indexOf(']');
        return Integer.parseInt(parameterName.substring(startIndex, endIndex));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa UTF-8 cho phản hồi

        try {

            String name = request.getParameter("product.name");
            boolean highlight = Boolean.parseBoolean(request.getParameter("product.highlight"));
            String brand = request.getParameter("product.brand");
            String description = request.getParameter("product.description");

            Long categoryId =Long.valueOf(request.getParameter("product.category.id"));
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(categoryId);

            Part sizeTableImagePart = request.getPart("product.sizeConversionTable");

            ProductDTO product = new ProductDTO(name,highlight,brand,description,categoryDTO,sizeTableImagePart);

            // Duyệt qua từng variant trong request và xử lý ảnh
            for (Part part : request.getParts()) {
                String partName = part.getName();

                if (partName.startsWith("productVariants")) {
                    int index = getIndexFromParameter(partName);

                    while (product.getProductVariants().size() <= index) {
                        product.getProductVariants().add(new ProductVariantDTO());
                    }

                    ProductVariantDTO variant = product.getProductVariants().get(index);

                    if (partName.endsWith(".price")) {
                        variant.setPrice(Double.parseDouble(request.getParameter(partName)));
                    } else if (partName.endsWith(".color")) {
                        variant.setColor(request.getParameter(partName));
                    } else if (partName.endsWith(".size")) {
                        variant.setSize(request.getParameter(partName));
                    } else if (partName.endsWith(".quantity")) {
                        variant.setQuantity(Integer.parseInt(request.getParameter(partName)));
                    } else if (part.getContentType() != null && part.getContentType().startsWith("image")) {
                        variant.setImage(part);
                    }
                }
            }

            if(product != null) {
                product.setRealPathFile(getServletContext().getRealPath("/"));
                product = productService.save(product);
                if(product != null) {
                    objectMapper.writeValue(response.getWriter(), "Thêm sản phẩm thành công !");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
                    objectMapper.writeValue(response.getWriter(), "Có lỗi trong khi thêm sản phẩm !");
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
            objectMapper.writeValue(response.getWriter(), "Invalid number format");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
            objectMapper.writeValue(response.getWriter(), "File processing error");
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
            objectMapper.writeValue(response.getWriter(), "Servlet error");
        }
    }
}