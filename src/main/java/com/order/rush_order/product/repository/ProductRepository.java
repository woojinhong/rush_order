package com.order.rush_order.product.repository;


import com.order.rush_order.product.entity.Product;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findByName(String name);

}
