package com.order.rush_order.member.repository;


import com.order.rush_order.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
