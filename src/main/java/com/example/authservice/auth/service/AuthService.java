package com.example.authservice.auth.service;

import com.example.authservice.auth.dto.request.LoginRequest;
import com.example.authservice.auth.dto.request.RegisterRequest;
import com.example.authservice.auth.dto.response.AuthResponse;
import com.example.authservice.auth.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}