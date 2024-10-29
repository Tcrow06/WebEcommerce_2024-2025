package com.webecommerce.entity.order;

import javax.persistence.*;

@Entity
@Table(name = "order_info")
public class OrderInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "orderInfo")
    private OrderEntity order;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
