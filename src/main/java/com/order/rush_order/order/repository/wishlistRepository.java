package com.order.rush_order.order.repository;


import com.order.rush_order.order.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface wishlistRepository extends JpaRepository<Wishlist,Long> {

}
