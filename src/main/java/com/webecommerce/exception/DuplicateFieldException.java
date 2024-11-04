package com.webecommerce.exception;

public class DuplicateFieldException extends RuntimeException{
    private String fieldName;

    public DuplicateFieldException(String fieldName) {
        super("Duplicate field: " + fieldName);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
