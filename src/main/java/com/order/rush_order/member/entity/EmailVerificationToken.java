package com.order.rush_order.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
public class EmailVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private LocalDateTime expiryDate;

    public static EmailVerificationToken generateToken(Users user) {
        EmailVerificationToken token = new EmailVerificationToken();
        token.user = user;
        token.token = UUID.randomUUID().toString();
        token.expiryDate = LocalDateTime.now().plusHours(24); // 유효기간 24시간
        return token;
    }
}


