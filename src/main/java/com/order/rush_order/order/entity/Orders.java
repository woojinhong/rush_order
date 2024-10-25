package com.order.rush_order.order.entity;

import com.order.rush_order.common.entity.Timestamp;
import com.order.rush_order.member.entity.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor

public class Orders extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문 상태
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 주문 상품의 총 합
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Builder
    public Orders(Users user, BigDecimal totalPrice, OrderStatus status) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public static Orders toEntity(Users user){
        return Orders.builder()
                .user(user)
                .totalPrice(BigDecimal.ZERO)
                .status(OrderStatus.PENDING)
                .build();
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
