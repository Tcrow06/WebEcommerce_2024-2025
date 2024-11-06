package com.webecommerce.entity.other;

import com.webecommerce.constant.EnumAccountStatus;
import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.constant.EnumRoleAccount;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.people.OwnerEntity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumAccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private EnumRoleAccount role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private OwnerEntity owner;

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

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnumAccountStatus getStatus() {
        return status;
    }

    public void setStatus(EnumAccountStatus status) {
        this.status = status;
    }

    public EnumRoleAccount getRole() {
        return role;
    }

    public void setRole(EnumRoleAccount role) {
        this.role = role;
    }
}
