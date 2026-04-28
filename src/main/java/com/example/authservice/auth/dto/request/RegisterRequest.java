package com.example.authservice.auth.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Fullname is required")
    private String fullname;

    @NotBlank(message = "Username is required")
    @Size(min = 4, message = "Username must be >= 4 chars")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be >= 6 chars")
    private String password;
}