package com.example.authservice.auth.service.impl;

import com.example.authservice.auth.dto.request.LoginRequest;
import com.example.authservice.auth.dto.request.RegisterRequest;
import com.example.authservice.auth.dto.response.AuthResponse;
import com.example.authservice.auth.dto.response.UserResponse;
import com.example.authservice.auth.entity.Role;
import com.example.authservice.auth.entity.User;
import com.example.authservice.auth.repository.RoleRepository;
import com.example.authservice.auth.repository.UserRepository;
import com.example.authservice.auth.service.AuthService;
import com.example.authservice.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public UserResponse register(RegisterRequest request) {


        var role = roleRepository.findByName("ROLE_USER")
                .orElseThrow();

        var user = User.builder()
                .fullname(request.getFullname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .fullname(savedUser.getFullname())
                .username(savedUser.getUsername())
                .role(
                        savedUser.getRoles()
                                .stream()
                                .findFirst()
                                .map(r -> r.getName().replace("ROLE_",""))
                                .orElse("UNKNOW")
                )
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();
    }
}
