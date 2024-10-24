package com.hallyugo.hallyugo.common;

import com.hallyugo.hallyugo.common.exception.AuthenticationNotFoundException;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() {

    }

    public static Long getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null) {
            throw new AuthenticationNotFoundException(ExceptionCode.AUTHENTICATION_NOT_FOUND);
        }

        return Long.parseLong(authentication.getName());
    }
}
