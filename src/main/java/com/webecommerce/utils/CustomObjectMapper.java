package com.webecommerce.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        this.registerModule(new JavaTimeModule());
    }
}
