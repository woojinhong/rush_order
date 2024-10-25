package com.order.rush_order.order.entity;

import com.order.rush_order.common.entity.Timestamp;
import com.order.rush_order.member.entity.Users;
import com.order.rush_order.product.entity.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Builder
    public Wishlist(Product product, Users user) {
        this.product = product;
        this.user = user;
    }
}
