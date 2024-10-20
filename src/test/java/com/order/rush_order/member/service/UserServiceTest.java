package com.order.rush_order.member.service;

import com.order.rush_order.member.dto.UserSignUpRequestDto;
import com.order.rush_order.member.entity.Users;
import com.order.rush_order.member.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 통합 테스트")
    void signUp() {
        UserSignUpRequestDto dto = new UserSignUpRequestDto(
                "woojin@gmail.com",
                "12345678",
                "woojin",
                "01012345678",
                "서울"
        );

        ResponseEntity<?> response = userService.signUp(dto);

        assertThat(response.getBody()).isEqualTo("회원가입 성공");

        // DB에서 저장된 사용자 확인
        boolean isSaved = userRepository.existsByEmail(dto.getEmail());

        assertTrue(isSaved);

    }
}

