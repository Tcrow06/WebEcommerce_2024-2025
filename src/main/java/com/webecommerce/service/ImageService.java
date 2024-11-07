package com.webecommerce.service;

import java.io.File;

public interface ImageService {
    int DEFAULT_WIDTH = 200;
    int DEFAULT_HEIGHT = 200;
    String DOT_EXTENDS = ".JPG";

    File getFile();

    String getFolderUpload();

    void saveImageToDisk();
    boolean delete(String path);

    public File getFile(String path);
    public String getPathToSaveDB(String path);

}
