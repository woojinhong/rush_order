package com.order.rush_order.member.repository;

import com.order.rush_order.member.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@ActiveProfiles("test")  // 테스트 프로파일을 명시
class UsersRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("이메일 중복 테스트")
    void existsByEmail() {

        Users users = Users.builder()
                .email("woojin@gmail.com")
                .password("encoded")
                .name("woojin")
                .contact("01012345678")
                .address("서울")
                .build();
        userRepository.save(users);


        boolean exists = userRepository.existsByEmail("woojin@gmail.com");


        assertTrue(exists);
    }
}