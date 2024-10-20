package com.order.rush_order.wishlist.repository;


import com.order.rush_order.wishlist.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface wishlistItemRepository extends JpaRepository<WishlistItem,Long> {

}
