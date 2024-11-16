package com.webecommerce.entity.order;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "return_order")
public class ReturnOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status")
    private int status;

    @Column(name = "quantity_return")
    private Long quantityReturn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_detail_id", referencedColumnName = "id")
    private OrderDetailEntity orderDetail;

    public Long getQuantityReturn() {
        return quantityReturn;
    }

    public void setQuantityReturn(Long quantityReturn) {
        this.quantityReturn = quantityReturn;
    }

    public OrderDetailEntity getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailEntity orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
