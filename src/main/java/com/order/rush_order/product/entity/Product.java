package com.order.rush_order.product.entity;

import com.order.rush_order.common.entity.Timestamp;
import com.order.rush_order.product.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private float price;

    @Column(nullable = false)
    private long stock;

    @Builder
    public Product(String name, String description, float price, long stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public static Product toEntity(ProductRequestDto dto){
        return new Product (
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStock()
                );
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
