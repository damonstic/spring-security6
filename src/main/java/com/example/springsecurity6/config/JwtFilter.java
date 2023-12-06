package com.example.springsecurity6.config;

import com.example.springsecurity6.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Resource
    JwtUtil jwtUtil;

    @Resource
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("token");//从请求头中获取token
        if (jwtToken != null && !jwtToken.isEmpty() && jwtUtil.checkToken(jwtToken)){
            try {//token可用
                Claims claims = jwtUtil.getTokenBody(jwtToken);
                String userName = (String) claims.get("userName");
                UserDetails user = userService.loadUserByUsername(userName);
                if (user != null){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e){
                log.error(e.getMessage());
            }
        }else {
            log.warn("token is null or empty or out of time, probably user is not log in !");
        }
        filterChain.doFilter(request, response);//继续过滤
    }
}