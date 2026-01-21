package com.spiderpool.common.utils;

import com.spiderpool.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {

    @Autowired
    private JwtConfig jwtConfig;

    private static String JWT_SECRET;

    private long JWT_EXPIRE_MILLIS;

    @PostConstruct
    private void init(){
        this.JWT_SECRET = jwtConfig.getSecret();
        this.JWT_EXPIRE_MILLIS = jwtConfig.getExpire();
    }

    private  SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    public  Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public  Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Map<String, Object> extraClaims) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(extraClaims.get("username").toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRE_MILLIS))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean parseToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            log.warn("JWT Token 已过期：{}", e.getMessage());
            return false;
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            log.error("JWT Token 格式错误：{}", e.getMessage());
            return false;
        } catch (io.jsonwebtoken.SignatureException e) {
            log.error("JWT Token 签名验证失败：{}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("JWT Token 验证失败：{}", e.getMessage());
            return false;
        }
    }
}