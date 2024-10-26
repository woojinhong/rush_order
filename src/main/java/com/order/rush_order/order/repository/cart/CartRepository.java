package com.order.rush_order.order.repository.cart;

import com.order.rush_order.member.entity.Users;
import com.order.rush_order.order.dto.response.CartItemDto;
import com.order.rush_order.order.entity.Cart;
import com.order.rush_order.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByUserAndProduct(Users user, Product product);

    List<CartItemDto> findAllByUser(Users user);

    Optional<Cart> findByUserAndProduct(Users user, Product product);

    List<Cart> findByUser(Users user);
}
