package com.webecommerce.service.impl;

import com.webecommerce.dto.MessageDTO;
import com.webecommerce.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

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
        return realPath + "static"+ File.separator + "img" +  File.separator + "product";
    }

    @Override
    public MessageDTO saveImageToDisk() {
        MessageDTO messageDTO = new MessageDTO();
        try {
            String mimeType = path.getContentType();
            System.out.println("MIME Type: " + mimeType);

            if (mimeType == null || !mimeType.startsWith("image")) {
                System.out.println("Đây không phải là một file ảnh hợp lệ.");
                messageDTO.setMessage("Đây không phải là một file ảnh hợp lệ.");
                messageDTO.setStatus("error");
                return messageDTO;
            }


            File tempFile = new File(getFolderUpload() + File.separator + "temp_" + fileName);
            path.write(tempFile.getAbsolutePath());


            File outputFile = getFile();
            if (mimeType.equals("image/jpeg") || mimeType.equals("image/png")) {
                try{
                    BufferedImage image = ImageIO.read(tempFile);
                    ImageIO.write(image, "jpg", outputFile);
                    System.out.println("Đã lưu ảnh dưới dạng JPG tại: " + outputFile.getAbsolutePath());
                    messageDTO.setStatus("success");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    messageDTO.setMessage("Định dạng ảnh gốc không được hỗ trợ.Có vẻ bạn đã ép kiểu ảnh không hợp lê. Chỉ xử lý ảnh gốc JPG, PNG, WEBP. Vui lòng chọn ảnh khác");
                    messageDTO.setStatus("error");
                    return messageDTO;
                }
            }

            else if (mimeType.equals("image/webp")) {
                Thumbnails.of(tempFile)
                        .outputFormat("jpg")
                        .toFile(outputFile);
                System.out.println("Ảnh WebP đã được chuyển đổi và lưu tại: " + outputFile.getAbsolutePath());
                messageDTO.setStatus("success");

            }
            else {
                System.out.println("Định dạng ảnh gốc không được hỗ trợ. Chỉ xử lý JPG, PNG, WEBP. Vui lòng chọn ảnh khác");
                messageDTO.setMessage("Định dạng ảnh gốc không được hỗ trợ. Chỉ xử lý JPG, PNG, WEBP. Vui lòng chọn ảnh khác");
                messageDTO.setStatus("error");
            }

            if (tempFile.exists() && !tempFile.delete()) {
                System.out.println("Không thể xóa file tạm.");
            }
            return messageDTO;

        } catch (IOException e) {
            System.out.println("Lỗi khi lưu ảnh: " + e.getMessage());
            messageDTO.setMessage("Định dạng ảnh gốc không được hỗ trợ.Có vẻ bạn đã ép kiểu ảnh không hợp lê. Chỉ xử lý ảnh gốc JPG, PNG, WEBP. Vui lòng chọn ảnh khác");
            messageDTO.setStatus("error");
            return messageDTO;
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