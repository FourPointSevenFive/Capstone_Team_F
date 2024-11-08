package com.hallyugo.hallyugo.auth;

import com.hallyugo.hallyugo.auth.domain.UserToken;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.common.exception.InvalidJwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private final Key key;
    private final Long accessTokenExpiry;
    private final Long refreshTokenExpiry;

    public JwtProvider(
            @Value("${spring.auth.jwt.secret-key}") final String secretKey,
            @Value("${spring.auth.jwt.access-token-expiry}") final Long accessTokenExpiry,
            @Value("${spring.auth.jwt.refresh-token-expiry}") final Long refreshTokenExpiry
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpiry = accessTokenExpiry;
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    // AccessToken, RefreshToken을 생성
    public UserToken generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + accessTokenExpiry);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + refreshTokenExpiry))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return UserToken.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // Jwt 토큰을 복호화해 토큰 정보를 꺼냄
    public Authentication getAuthentication(String accessToken) {
        // Jwt 토큰 복호화
        Claims claims = parseClaims(accessToken);

        /*
        String authoritiesString = claims.get(AUTHORITIES_KEY).toString();
        if (authoritiesString.isEmpty()) {
            throw new InvalidJwtException(ExceptionCode.INVALID_TOKEN);
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
        */

        // 따로 User 권한을 설정하지 않음
        UserDetails principal = new User(claims.getSubject(), "", Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(principal, "", Collections.emptyList());
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("{}", ExceptionCode.INVALID_TOKEN);
            throw new InvalidJwtException(ExceptionCode.INVALID_TOKEN);
        } catch (ExpiredJwtException | IllegalArgumentException e) {
            log.info("{}", ExceptionCode.EXPIRED_TOKEN);
            throw new InvalidJwtException(ExceptionCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("{}", ExceptionCode.UNSUPPORTED_TOKEN);
            throw new InvalidJwtException(ExceptionCode.UNSUPPORTED_TOKEN);
        }
    }

    // accessToken
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}