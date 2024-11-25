package com.webecommerce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webecommerce.dto.CategoryDTO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.dto.discount.BillDiscountDTO;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-product","/api-add-product","/api-update-product"})
@MultipartConfig
public class ProductAPI extends HttpServlet {
    @Inject
    IProductService productService;

    @Inject
    IProductVariantService productVariantService;

    private ObjectMapper objectMapper = new ObjectMapper();

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");

        try {
            String idProduct = req.getParameter("product.id");

            if (idProduct != null) {
                Long id = Long.valueOf(idProduct);
                stopSellingProduct(req,resp,new ProductDTO(id));
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getWriter(), "Lỗi xử lý: " + e.getMessage());
        }
    }

    private int getIndexFromParameter(String parameterName) {
        int startIndex = parameterName.indexOf('[') + 1;
        int endIndex = parameterName.indexOf(']');
        return Integer.parseInt(parameterName.substring(startIndex, endIndex));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getServletPath();
        if (action.equals("/api-add-product"))
            addProduct(request,response);
        else if (action.equals("/api-update-product"))
            updateProduct(request,response);

    }

    private void addProduct (HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    objectMapper.writeValue(response.getWriter(), "Có lỗi trong khi thêm sản phẩm !");
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(), "Invalid number format");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(response.getWriter(), "File processing error");
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(response.getWriter(), "Servlet error");
        }
    }

    private void updateProduct (HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long id = Long.valueOf(request.getParameter("product.id"));
            String name = request.getParameter("product.name");
            boolean highlight = Boolean.parseBoolean(request.getParameter("product.highlight"));
            String brand = request.getParameter("product.brand");
            String description = request.getParameter("product.description");

            Long categoryId =Long.valueOf(request.getParameter("product.category.id"));
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(categoryId);

            Part sizeTableImagePart = request.getPart("product.sizeConversionTable");

            ProductDTO product = new ProductDTO(id,name,highlight,brand,description,categoryDTO,sizeTableImagePart);


            int index = 0;
            while (true) {
                ProductVariantDTO productVariant = new ProductVariantDTO();

                String i = request.getParameter("productVariants[" + index + "].index");
                if (i == null) break; // Không còn variant nào nữa

                String variantId = request.getParameter("productVariants[" + index + "].id");
                if (variantId != null && !variantId.equals("undefined")) {
                    productVariant.setId(Long.valueOf(variantId));
                }
                productVariant.setPrice(Double.parseDouble(request.getParameter("productVariants[" + index + "].price")));
                productVariant.setColor(request.getParameter("productVariants[" + index + "].color"));
                productVariant.setSize(request.getParameter("productVariants[" + index + "].size"));
                productVariant.setQuantity( Integer.parseInt(request.getParameter("productVariants[" + index + "].quantity")));
                productVariant.setImage(request.getPart("productVariants[" + index + "].image"));

                product.getProductVariants().add(productVariant);

                index++;
            }

            if(product != null) {
                product.setRealPathFile(getServletContext().getRealPath("/"));
                product = productService.update(product);
                if(product != null) {
                    objectMapper.writeValue(response.getWriter(), "Chỉnh sửa sản phẩm thành công !");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    objectMapper.writeValue(response.getWriter(), "Có lỗi trong khi thêm sản phẩm !");
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(), "Invalid number format");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(response.getWriter(), "File processing error");
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(response.getWriter(), "Servlet error");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(response.getWriter(), "Lỗi khác");
        }
    }

    private void stopSellingProduct (HttpServletRequest request, HttpServletResponse response, ProductDTO productDTO) throws IOException {
        productDTO = productService.stopSelling(productDTO.getId());
        if(productDTO != null) {
            objectMapper.writeValue(response.getWriter(), "Ngừng kinh doanh sản phẩm thành công !");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(), "Ngừng kinh doanh sản phẩm thất bại !");
        }
    }

}