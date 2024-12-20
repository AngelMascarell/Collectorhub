package com.collectorhub.collectorhub.services.impl;

import com.collectorhub.collectorhub.database.entities.UserEntity;
import com.collectorhub.collectorhub.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "RhV5sTraJPAdJi5HJKMhRwnGBpMaH4k0FSJflMup+Xrm2McCKAxJdy5GQFq4Sj0";

    @Override
    public String getToken(UserEntity user) {
        return getToken(new HashMap<>(), user);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private static final ConcurrentHashMap<String, Long> revokedTokens = new ConcurrentHashMap<>();

    @Override
    public void revokeToken(String token) {
        revokedTokens.put(token, System.currentTimeMillis());
    }

    @Override
    public boolean isTokenRevoked(String token) {
        return revokedTokens.containsKey(token);
    }

    @Override
    public void cleanExpiredTokens(long expirationTimeInMillis) {
        long currentTime = System.currentTimeMillis();
        revokedTokens.entrySet().removeIf(entry -> currentTime - entry.getValue() > expirationTimeInMillis);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }



    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000L * 60 * 60 * 24 * 7))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
