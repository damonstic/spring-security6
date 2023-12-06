package com.example.springsecurity6.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
