package com.order.rush_order.member.dto;


import lombok.Getter;


@Getter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String contact;
    private String address;
    private String adminToken = "";
}
