package com.order.rush_order.member.service;

import com.order.rush_order.common.jwt.JwtUtil;
import com.order.rush_order.member.dto.UserLoginDto;
import com.order.rush_order.member.dto.UserSignUpDto;
import com.order.rush_order.member.entity.EmailVerificationToken;
import com.order.rush_order.member.entity.Users;
import com.order.rush_order.member.repository.EmailVerificationTokenRepository;
import com.order.rush_order.member.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final EmailVerificationTokenRepository tokenRepository;

    @Transactional
    public void signUp(UserSignUpDto userSignUpDto) {

        //dto -> entity
        
        String pwdEncode = passwordEncoder.encode(userSignUpDto.getPassword());

        // 이메일 중복 체크
        validateDuplicateEmail(userSignUpDto.getEmail());

        // dto -> entity
        Users users = Users.toEntity(userSignUpDto, pwdEncode);

        userRepository.save(users);
        // 이메일 인증 토큰 생성 및 저장
        EmailVerificationToken token = EmailVerificationToken.generateToken(users);
        tokenRepository.save(token);

        // 이메일 전송
        String link = "http://localhost:8080/api/user/verify?token=" + token.getToken();
        emailService.sendEmail(users.getEmail(), "이메일 인증", "다음 링크를 클릭하여 인증을 완료하세요: " + link);
    }

    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }
    }


    public void login(UserLoginDto loginDto, HttpServletResponse res) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        // 사용자 확인
        Users user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );

        // 계정 활성화 여부 확인
        if (!user.isEnabled()) {
            throw new IllegalStateException("이메일 인증이 완료되지 않았습니다.");
        }

        // 비밀번호 확인
        if(passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        // JWT 생성 추가
        String token = jwtUtil.createToken(user.getEmail());
        jwtUtil.addJwtToCookie(token, res);


    }
    @Transactional
    public void verifyEmail(String token) {
        EmailVerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("토큰이 만료되었습니다.");
        }

        Users user = verificationToken.getUser();
        user.setEnabled(true); // 사용자 계정 활성화
        userRepository.save(user);
    }
}
