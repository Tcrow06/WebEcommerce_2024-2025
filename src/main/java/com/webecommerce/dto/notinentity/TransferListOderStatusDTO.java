package com.webecommerce.dto.notinentity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransferListOderStatusDTO {
    private Long id;
    private LocalDateTime date;
    private List<String> imgList = new ArrayList<>(); // Danh sách ảnh

    public TransferListOderStatusDTO() {}

    public TransferListOderStatusDTO(Long id, LocalDateTime date, List<String> imgList) {
        this.id = id;
        this.date = date;
        this.imgList = imgList;
    }

    // Getter và Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public void addImg(String img) {
        this.imgList.add(img);
    }
}
