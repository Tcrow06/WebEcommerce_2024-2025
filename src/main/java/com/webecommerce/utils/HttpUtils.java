package com.webecommerce.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtils {
    private  String value;

    public HttpUtils(String value) {
        this.value = value;
    }

    ///covert từ json string to model
    public  <T> T toModel (Class <T> tClass) {
        try {
            return new ObjectMapper().readValue(value,tClass) ;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static HttpUtils of (BufferedReader reader) {
        StringBuilder sb = new StringBuilder() ;
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return  new HttpUtils (sb.toString());
    }
}
