package com.collectorhub.collectorhub.controller;

import com.collectorhub.collectorhub.controller.request.LoginRequest;
import com.collectorhub.collectorhub.controller.request.RegisterRequest;
import com.collectorhub.collectorhub.controller.response.AuthResponse;
import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.services.AuthService;
import com.collectorhub.collectorhub.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        jwtService.revokeToken(token);

        return ResponseEntity.ok("Logout successful");
    }

}
