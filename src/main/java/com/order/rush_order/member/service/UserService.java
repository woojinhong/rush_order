//package com.order.rush_order.member.service;
//
//import com.order.rush_order.member.dto.SignUpRequestDto;
//import com.order.rush_order.member.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final TokenProvider tokenProvider;
//
//    @Transactional
//    public ResponseEntity<?> signUp(SignUpRequestDto userSignUpDto) {
//
//        //dto -> entity
//
//
//        System.out.println(user);
//
//        //이메일 중복 체크
//        validateEmail(user);
//        //비밀번호 엔코딩
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        //권한 부여
//        user.setRole(Role.ROLE_USER);
//        //유저 상태(탈퇴 여부)
//        user.setStatus(true);
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok("부모 회원가입 성공");
//    }
//
//
//}
