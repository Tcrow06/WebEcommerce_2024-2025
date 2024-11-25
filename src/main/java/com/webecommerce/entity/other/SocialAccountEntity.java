package com.webecommerce.entity.other;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.entity.people.CustomerEntity;

import javax.persistence.*;

@Entity
@Table(name = "social_account")
public class SocialAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumAccountStatus status =EnumAccountStatus.ACTIVE;

    private String ggID;
    private String fbID;

    public String getGgID() {
        return ggID;
    }

    public void setGgID(String ggID) {
        this.ggID = ggID;
    }

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }


    public EnumAccountStatus getStatus() {
        return status;
    }

    public void setStatus(EnumAccountStatus status) {
        this.status = status;
    }
}
