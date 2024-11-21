package com.hallyugo.hallyugo.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    // User
    UNAUTHORIZED_USER(401, "Unauthorized user. You need to login"),
    FORBIDDEN_USER(403, "Forbidden"),

    // Sign in / Log in
    DUPLICATE_USER_REGISTRATION(2001, "User already registered"),
    NOT_FOUND_USERNAME(2002, "User can not find in database"),
    NOT_FOUND_USERID(2003, "User can not find in database"),
    LOGGED_OUT_USER(2004, "User has been logged out."),
    TOKEN_USER_MISMATCH(2005, "User information in the token does not match."),

    // Auth
    INVALID_TOKEN(3001, "Invalid JWT token"),
    INVALID_REFRESH_TOKEN(3002, "Invalid refresh token"),
    EXPIRED_TOKEN(3003, "Expired JWT token"),
    UNSUPPORTED_TOKEN(3004, "Unsupported JWT token"),
    EMPTY_CLAIMS(3005, "JWT claims string is empty"),
    AUTHENTICATION_NOT_FOUND(3006, "Authentication information is missing in the Security Context."),

    // Entity
    ENTITY_NOT_FOUND(4001, "No results found"),
    UNSUPPORTED_ENTITY_TYPE(4002, "Unsupported entity type encountered");

    private final int code;
    private final String message;
}
