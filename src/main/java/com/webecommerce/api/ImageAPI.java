package com.webecommerce.api;

import com.webecommerce.service.ImageService;
import com.webecommerce.service.impl.ImageServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(urlPatterns = {"/api-image"})
public class ImageAPI extends HttpServlet {
    @Inject
    ImageServiceImpl imageService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathName = request.getParameter("path");
        imageService.setRealPath(getServletContext().getRealPath("/"));

        File image = imageService.getFile(pathName);

        if(image.exists()){
            String minType = getServletContext().getMimeType(image.getName());
            if (minType == null) {
                minType = "application/octet-stream";
            }
            response.setContentType(minType);
            response.setContentLengthLong(image.length());
            Files.copy(image.toPath(), response.getOutputStream());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
