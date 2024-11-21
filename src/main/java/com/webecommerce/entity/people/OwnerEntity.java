package com.webecommerce.entity.people;

import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.review.ReviewFeedbackEntity;

import javax.persistence.*;

@Entity
@Table(name = "[owner]")
public class OwnerEntity extends UserEntity {

    @OneToOne(mappedBy = "owner")
    private AccountEntity account;

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
