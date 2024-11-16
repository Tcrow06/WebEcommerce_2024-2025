package com.webecommerce.dto.response.people;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private  String avatar;
    private String role;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
