package com.webecommerce.service.impl;

import com.webecommerce.service.ImageService;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServlet;

public class ImageServiceImpl extends HttpServlet implements ImageService {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Gọi super để đảm bảo cấu hình Servlet không bị mất
        // Các xử lý khởi tạo khác nếu cần
    }

    //Lưu tạm ảnh vào folder project
    private int width, height;
    private String fileName, id;
    private Part path;
    private String realPath;

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Part getPath() {
        return path;
    }

    public void setPath(Part path) {
        this.path = path;
        this.fileName = path.getSubmittedFileName();
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
    }

    @Override
    public File getFile() {
           return new File(getFolderUpload()  + File.separator + this.id + DOT_EXTENDS);
    }

    @Override
    public String getFolderUpload() {
         String path = realPath + File.separator + "static"+ File.separator + "img" +  File.separator + "product";
        String path1 = realPath + "static"+ File.separator + "img" +  File.separator + "product";
        return realPath + "static"+ File.separator + "img" +  File.separator + "product";
    }

    private BufferedImage getBufferedImage() {
        try {
            File file = getFile();
            if (file == null || !file.exists()) {
                System.out.println("File không tồn tại hoặc là null.");
                return null; // hoặc xử lý theo ý muốn
            }


            if (!file.canRead()) {
                System.out.println("Không có quyền đọc file tại đường dẫn: " + file.getAbsolutePath());
                return null;
            }

            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage == null) {
                System.out.println("Không thể đọc file ảnh hoặc định dạng ảnh không hợp lệ.");
                return null; // hoặc xử lý theo ý muốn
            }

            return bufferedImage;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi đọc file ảnh: " + e.getMessage(), e);
        }
    }

    @Override
    public void saveImageToDisk() {
        try {
            path.write(getFile().getAbsolutePath());
            this.width = this.width > 0 ? this.width : getBufferedImage().getWidth();
            this.height = this.height > 0 ? this.height : getBufferedImage().getWidth();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean delete(String path) {
        return getFile(path).delete();
    }

    @Override
    public File getFile(String path) {
        return new File(getFolderUpload() + File.separator + path + DOT_EXTENDS);
    }

}
