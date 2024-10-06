package com.webecommerce.entity;

import javax.persistence.*;

@Entity()
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    Long id;
    @Column
    String name;

    @Column
    String description;


    public Product(){}

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
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

}

