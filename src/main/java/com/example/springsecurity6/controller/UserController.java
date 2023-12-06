package com.example.springsecurity6.controller;

import cn.hutool.core.util.IdUtil;
import com.example.springsecurity6.config.JwtUtil;
import com.example.springsecurity6.entity.HnResult;
import com.example.springsecurity6.entity.LoginRequest;
import com.example.springsecurity6.entity.User;
import com.example.springsecurity6.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    @Resource
    AuthenticationManager authenticationManager;

    @PostMapping("login")
    public HnResult doLogin(@RequestBody LoginRequest request){
        try{
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
            Authentication authentication = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = JwtUtil.getToken(userDetails.getUsername());
            return HnResult.ok("登录成功").setData(jwtToken);
        } catch (Exception e){
            log.error(e.getMessage());
            log.error("userName or password is not correct");
            return HnResult.error("登录失败");
        }
    }

    @Resource
    UserService userService;

    @Resource
    PasswordEncoder passwordEncoder;

    @PostMapping("register")
    public HnResult doRegister(@RequestBody User user){
        try {
            if (user.getPassword() != null && !user.getPassword().isEmpty()){
                String password = passwordEncoder.encode(user.getPassword());
                user.setPassword(password);
                user.setUserId(IdUtil.getSnowflakeNextIdStr());
                if (userService.insertUser(user) == null){
                    throw new Exception("用户名已存在");
                }
                String jwtToken = JwtUtil.getToken(user.getUserName());
                return HnResult.ok().setData(jwtToken);
            }else
                throw new Exception("密码为空");
        }catch (Exception e){
            log.error(e.getMessage());
            return HnResult.error("注册失败" + e.getMessage());
        }
    }

    @PostMapping("check")
    public HnResult doCheck(){
        log.info("权限验证成功");
        return HnResult.ok();
    }

}
