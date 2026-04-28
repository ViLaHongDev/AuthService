package com.example.authservice.auth.dto.response;

import lombok.*;

@Data
@Builder
public class AuthResponse {
    private String token;
}
