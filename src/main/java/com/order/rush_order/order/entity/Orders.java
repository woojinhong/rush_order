package com.order.rush_order.order.entity;

import com.order.rush_order.member.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문 상태
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 주문 상품의 총 합
    @Column(nullable = false)
    private float total_price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    // created at
}
