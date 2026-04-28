package com.example.authservice.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
//    private UUID id;
    private String fullname;
    private String username;
    private String role;
}