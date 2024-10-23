package com.hallyugo.hallyugo.user.domain.response;

import com.hallyugo.hallyugo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String username;
    private String nickname;
    private LocalDateTime createdAt;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getNickname(), user.getCreatedAt());
    }
}
