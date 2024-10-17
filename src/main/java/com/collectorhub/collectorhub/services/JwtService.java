package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String getToken(UserEntity user);

    String getUsernameFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
