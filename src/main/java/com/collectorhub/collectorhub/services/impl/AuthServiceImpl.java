package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.controller.request.LoginRequest;
import com.collectorhub.collectorhub.controller.request.RegisterRequest;
import com.collectorhub.collectorhub.controller.response.AuthResponse;
import com.collectorhub.collectorhub.database.entities.RoleEntity;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.database.repositories.RoleRepository;
import com.collectorhub.collectorhub.database.repositories.UserRepository;
import com.collectorhub.collectorhub.services.AuthService;
import com.collectorhub.collectorhub.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());

        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .build();
    }


    @Override
    public AuthResponse register(RegisterRequest registerRequest) {

        RoleEntity role = roleRepository.findByName("USER");

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        UserEntity user = UserEntity.builder()
                .username(registerRequest.getUsername())
                .password(encodedPassword)
                .email(registerRequest.getEmail())
                .birthdate(registerRequest.getBirthdate())
                .registerDate(LocalDate.now())
                .mangas(new ArrayList<>())
                .premiumEndDate(null)
                .premiumStartDate(null)
                .isPremium(false)
                .role(role)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
