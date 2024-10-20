package com.order.rush_order.wishlist.repository;


import com.order.rush_order.wishlist.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface wishlistRepository extends JpaRepository<Wishlist,Long> {

}
