package com.webecommerce.entity.other;

import com.webecommerce.constant.EnumSendBy;
import com.webecommerce.entity.people.CustomerEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "[message]")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "send_by")
    private EnumSendBy sendBy;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumSendBy getSendBy() {
        return sendBy;
    }

    public void setSendBy(EnumSendBy sendBy) {
        this.sendBy = sendBy;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
