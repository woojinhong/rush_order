package com.order.rush_order.order.repository.order;


import com.order.rush_order.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {

}
