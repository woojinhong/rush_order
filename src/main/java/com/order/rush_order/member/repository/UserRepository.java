package com.order.rush_order.member.repository;


import com.order.rush_order.member.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {

    boolean existsByEmail(String email);


}
