package com.order.rush_order.order.entity;

import com.order.rush_order.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.query.Order;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private long quantity;

    // 주문 상품 가격
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public static OrderItem toEntity(Orders order, Product product, long quantity, BigDecimal price) {
        return OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
