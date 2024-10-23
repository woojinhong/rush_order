package com.order.rush_order.member.controller;

import com.order.rush_order.member.dto.UserSignUpRequestDto;
import com.order.rush_order.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // user sign up 성공
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequestDto signUpDto) {
        userService.signUp(signUpDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 이메일 인증 엔드포인트
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        userService.verifyEmail(token);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }

}

