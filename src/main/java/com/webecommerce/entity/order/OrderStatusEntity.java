package com.webecommerce.entity.order;

import com.webecommerce.constant.EnumOrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_status")
public class OrderStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumOrderStatus status;

    // Mỗi một trạng thái tại một thời điểm chỉ là của riêng một order nào đó
    @ManyToOne
    private OrderEntity order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }
}
