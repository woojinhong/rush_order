package com.order.rush_order.member.service;

import com.order.rush_order.member.dto.UserSignUpRequestDto;
import com.order.rush_order.member.entity.Users;
import com.order.rush_order.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<?> signUp(UserSignUpRequestDto userSignUpDto) {

        //dto -> entity
        
        String pwdEncode = passwordEncoder.encode(userSignUpDto.getPassword());

        // 이메일 중복 체크
        validateDuplicateEmail(userSignUpDto.getEmail());

        // dto -> entity
        Users users = Users.toEntity(userSignUpDto, pwdEncode);

        userRepository.save(users);

        return ResponseEntity.ok("회원가입 성공");
    }

    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }
    }


}
