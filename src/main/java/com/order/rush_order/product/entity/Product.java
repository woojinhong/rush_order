package com.order.rush_order.product.entity;

import com.order.rush_order.common.entity.Timestamp;
import com.order.rush_order.product.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
public class Product extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    private String description;

    // 상품 판매가
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private long stock = 0;

    @Builder
    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // 상품 수량 증가 메서드 (비즈니스 로직)
    public void increaseStock(long amount) {
        this.stock += amount;
    }

    // 상품 수량 감소 메서드 (비즈니스 로직)
    public void decreaseStock(long amount) {
        if (this.stock < amount) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stock -= amount;
    }
}
