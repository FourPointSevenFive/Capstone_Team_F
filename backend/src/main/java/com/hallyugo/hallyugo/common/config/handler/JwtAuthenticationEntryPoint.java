package com.hallyugo.hallyugo.common.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.common.exception.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionCode.UNAUTHORIZED_USER.getCode(), ExceptionCode.UNAUTHORIZED_USER.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(exceptionResponse));
    }
}