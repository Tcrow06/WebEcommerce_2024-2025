package com.webecommerce.service;

import java.io.File;

public interface ImageService {
    String DOT_EXTENDS = ".JPG";

    File getFile();

    String getFolderUpload();

    void saveImageToDisk();
    boolean delete(String path);

    public File getFile(String path);

}
