package com.example.springsecurity6.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements UserDetails {
    @TableField("userName")
    private String userName;

    @TableField("password")
    private String password;

    @TableId("userId")
    private String userId;

    @TableField("role")
    private String role;

    @Override//用户所拥有的权限，返回的列表中至少得有一个值，否则这个用户啥权限都没有
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override//实现UserDetails的getPassword方法，返回实体类的password
    public String getPassword() {
        return password;
    }

    @Override//这个方法是UserDetails中的方法，必须实现
    public String getUsername() {
        return userName;
    }

    public String getUserName(){//这个是mybatis-plus需要用到的方法
        return this.userName;
    }

    @Override//返回true，代表用户账号没过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override//返回true，代表用户账号没被锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override//返回true，代表用户密码没有过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override//返回true，代表用户账号还能够使用
    public boolean isEnabled() {
        return true;
    }


}
