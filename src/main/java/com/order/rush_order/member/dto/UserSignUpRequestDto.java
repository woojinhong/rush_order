package com.order.rush_order.member.dto;


import lombok.Getter;


@Getter
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String contact;
    private String address;



    // 테스트 용
    public UserSignUpRequestDto(String email, String password, String name, String contact, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }
}
