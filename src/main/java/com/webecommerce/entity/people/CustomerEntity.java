package com.webecommerce.entity.people;

import com.webecommerce.entity.cart.CartEntity;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.entity.other.*;
import com.webecommerce.entity.review.ProductReviewEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[customer]")
public class CustomerEntity extends UserEntity {

    @Column(name = "[loyalty_point]")
    private int loyaltyPoint;

    @OneToOne(mappedBy = "customer")
    private CartEntity cart;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<OrderInfoEntity> orderInfos = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<MessageEntity> messages = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<SearchHistoryEntity> searchHistories = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    private AccountEntity account;

    @OneToOne(mappedBy = "customer")
    private AccountEntity account;

    @OneToOne(mappedBy = "customer")
    private SocialAccountEntity socialAccount;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "social_account_id", referencedColumnName = "id")
//    private SocialAccountEntity socialAccount;



    @OneToMany(mappedBy = "customer")
    private List<ProductReviewEntity> productReviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "[customer_notification]", joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private List<NotificationEntity> notifications = new ArrayList<>();

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public List<OrderInfoEntity> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfoEntity> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public List<SearchHistoryEntity> getSearchHistories() {
        return searchHistories;
    }

    public void setSearchHistories(List<SearchHistoryEntity> searchHistories) {
        this.searchHistories = searchHistories;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public SocialAccountEntity getSocialAccount() {
        return socialAccount;
    }

    public void setSocialAccount(SocialAccountEntity socialAccount) {
        this.socialAccount = socialAccount;
    }

    public List<ProductReviewEntity> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewEntity> productReviews) {
        this.productReviews = productReviews;
    }

    public List<NotificationEntity> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationEntity> notifications) {
        this.notifications = notifications;
    }

    public int getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(int loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }
}
