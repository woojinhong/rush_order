package com.order.rush_order.member.entity;

import com.order.rush_order.member.dto.UserSignUpDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 11)
    private String contact;

    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private boolean enabled;  // 계정 활성화 여부

    @Builder
    public Users(String email, String password, String name, String contact, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    // 정적 팩토리 메서드
    public static Users toEntity(UserSignUpDto dto, String encodedPassword) {
        return Users.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .name(dto.getName())
                .contact(dto.getContact())
                .address(dto.getAddress())
                .build();
    }
    // 계정 활성화 메서드
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
