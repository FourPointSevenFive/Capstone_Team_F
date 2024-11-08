package com.hallyugo.hallyugo.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.common.exception.ExceptionResponse;
import com.hallyugo.hallyugo.common.exception.InvalidJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request);

        if (StringUtils.hasText(jwt)) {
            try {
                if (jwtProvider.validateToken(jwt)) {
                    Authentication authentication = jwtProvider.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (InvalidJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                ExceptionResponse exceptionResponse = new ExceptionResponse(e.getCode(), e.getMessage());
                response.getWriter().write(new ObjectMapper().writeValueAsString(exceptionResponse));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length()).trim();
        }
        return null;
    }
}
