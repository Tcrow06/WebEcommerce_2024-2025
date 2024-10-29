package com.webecommerce.entity.order;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "return_order")
public class ReturnOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status")
    private int status;

    // Mỗi return order là của một order detail
    @OneToOne
    @JoinColumn(name = "order_detail_id")
    private OrderDetailEntity orderDetail;

    public OrderDetailEntity getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailEntity orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
