package com.webecommerce.dto;

public class CategoryDTO extends BaseDTO<CategoryDTO> {

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CategoryDTO(Long id) {
        super(id);
    }

    public CategoryDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
