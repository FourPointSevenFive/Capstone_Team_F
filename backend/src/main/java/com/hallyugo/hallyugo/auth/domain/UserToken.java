package com.hallyugo.hallyugo.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserToken {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;
}
