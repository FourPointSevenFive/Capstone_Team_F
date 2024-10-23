package com.hallyugo.hallyugo.auth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "jwt", timeToLive = 60 * 60 * 24 * 7)
public class RefreshToken {
    private Long userId;
    @Id
    private String value;

    public RefreshToken updateToken(String newToken) {
        this.value = newToken;
        return this;
    }
}