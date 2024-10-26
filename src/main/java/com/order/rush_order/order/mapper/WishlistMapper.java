package com.order.rush_order.order.mapper;

import com.order.rush_order.member.entity.Users;
import com.order.rush_order.order.dto.response.WishlistItemDto;
import com.order.rush_order.order.dto.WishlistResponseDto;
import com.order.rush_order.order.entity.Wishlist;
import com.order.rush_order.product.entity.Product;

public class WishlistMapper {

    public static Wishlist toEntity(Users user, Product product){
        return Wishlist.builder()
                .user(user)
                .product(product)
                .build();
    }
}
