package com.webecommerce.entity.people;

import com.webecommerce.entity.other.AccountEntity;
import com.webecommerce.entity.review.ReviewFeedbackEntity;

import javax.persistence.*;

@Entity
@Table(name = "[owner]")
public class OwnerEntity extends UserEntity {

    @OneToOne(mappedBy = "owner")
    private AccountEntity account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_feedback_id", referencedColumnName = "id")
    private ReviewFeedbackEntity reviewFeedback;

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public ReviewFeedbackEntity getReviewFeedback() {
        return reviewFeedback;
    }

    public void setReviewFeedback(ReviewFeedbackEntity reviewFeedback) {
        this.reviewFeedback = reviewFeedback;
    }
}
