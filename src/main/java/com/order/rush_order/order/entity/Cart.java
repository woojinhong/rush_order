package com.order.rush_order.order.entity;

import com.order.rush_order.member.entity.Users;
import com.order.rush_order.product.entity.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(nullable = false)
    private long quantity;

    // 장바구니 특정 상품의 총 비용
    @Column(nullable = false)
    private BigDecimal cartPrice;

    @Builder
    public Cart(Product product, Users user, long quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
        this.cartPrice = calculateCartPrice();
    }

    public static Cart toEntity(Users user, Product product, long quantity){
        return Cart.builder()
                .product(product)
                .user(user)
                .quantity(quantity)
                .build();
    }

    // cartPrice 계산 메서드
    public BigDecimal calculateCartPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    // 수량 업데이트 메서드
    public void updateQuantity(long newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("수량은 음수일 수 없습니다.");
        }
        this.quantity = newQuantity;
        this.cartPrice = calculateCartPrice();  // 수량 변경 시 가격 재계산
    }

    // 상품 추가(수량 증가) 메서드
    public void addQuantity(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("추가 수량은 양수여야 합니다.");
        }
        this.quantity += amount;
        this.cartPrice = calculateCartPrice();  // 수량 증가 후 가격 재계산
    }

    // 상품 제거(수량 감소) 메서드
    public void subtractQuantity(long amount) {
        if (amount <= 0 || this.quantity < amount) {
            throw new IllegalArgumentException("잘못된 수량 감소 요청입니다.");
        }
        this.quantity -= amount;
        this.cartPrice = calculateCartPrice();  // 수량 감소 후 가격 재계산
    }
}
