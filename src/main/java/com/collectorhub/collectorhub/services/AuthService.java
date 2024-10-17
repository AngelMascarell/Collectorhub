package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.controller.request.LoginRequest;
import com.collectorhub.collectorhub.controller.request.RegisterRequest;
import com.collectorhub.collectorhub.controller.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse register(RegisterRequest registerRequest);

}
