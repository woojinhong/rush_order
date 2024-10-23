package com.order.rush_order.order.repository;


import com.order.rush_order.order.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface wishlistItemRepository extends JpaRepository<WishlistItem,Long> {

}
