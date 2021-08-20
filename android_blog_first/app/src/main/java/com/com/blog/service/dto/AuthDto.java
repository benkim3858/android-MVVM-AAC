package com.com.blog.service.dto;

import com.com.blog.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDto {
    private User user;
    private String token;
    private boolean isLogin;
}
