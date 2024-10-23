package com.order.rush_order.member.repository;


import com.order.rush_order.member.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);


}
