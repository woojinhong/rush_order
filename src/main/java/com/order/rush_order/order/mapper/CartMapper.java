package com.order.rush_order.order.mapper;

import com.order.rush_order.member.entity.Users;
import com.order.rush_order.order.dto.response.CartItemDto;
import com.order.rush_order.order.entity.Cart;
import com.order.rush_order.product.entity.Product;

public class CartMapper {

    public static CartItemDto toItemDto(Cart cart){
        Product product = cart.getProduct();  // Cart에서 Product 객체 가져오기

        return CartItemDto.builder()
                .cartId(cart.getId())
                .productId(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(cart.getQuantity())
                .cartPrice(cart.getCartPrice())  // 계산된 총 가격 반영
                .build();
    }
}

