package com.hallyugo.hallyugo.common.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.common.exception.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionCode.FORBIDDEN_USER.getCode(), ExceptionCode.FORBIDDEN_USER.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(exceptionResponse));
    }
}