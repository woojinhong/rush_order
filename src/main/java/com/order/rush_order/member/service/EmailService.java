package com.order.rush_order.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/**
 * 5. 전체 회원가입 및 인증 과정 요약
 *
 * 회원가입: 사용자가 회원가입하면 사용자 정보와 계정 상태(enabled=false)가 저장됩니다.
 * 이메일 전송: 이메일에 포함된 인증 링크가 사용자의 이메일로 전송됩니다.
 * 링크 클릭: 사용자가 링크를 클릭하면 /api/user/verify 엔드포인트가 호출됩니다.
 * 계정 활성화: verifyEmail 메서드가 실행되며, 사용자의 계정이 활성화됩니다(enabled=true).
 * 로그인 가능: 이제 활성화된 계정으로 로그인할 수 있습니다.
 */
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}