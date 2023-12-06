package com.example.springsecurity6;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@MapperScan("com.example.springsecurity6.mapper")
public class SpringSecurity6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity6Application.class, args);
    }

}
