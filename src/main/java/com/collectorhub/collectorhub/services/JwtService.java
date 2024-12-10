package com.collectorhub.collectorhub.services;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String getToken(UserEntity user);

    String getUsernameFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    void revokeToken(String token);

    public boolean isTokenRevoked(String token);

    public void cleanExpiredTokens(long expirationTimeInMillis);
}
