package com.order.rush_order.order.repository.payment;


import com.order.rush_order.order.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
