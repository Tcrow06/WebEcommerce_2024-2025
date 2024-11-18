package com.webecommerce.service;

import com.webecommerce.dto.MessageDTO;

import java.io.File;

public interface ImageService {
    String DOT_EXTENDS = ".JPG";

    File getFile();

    String getFolderUpload();

    MessageDTO saveImageToDisk();
    boolean delete(String path);

    public File getFile(String path);

}
