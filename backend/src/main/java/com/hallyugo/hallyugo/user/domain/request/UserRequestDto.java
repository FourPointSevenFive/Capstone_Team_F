package com.hallyugo.hallyugo.user.domain.request;

import com.hallyugo.hallyugo.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String nickname;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(this.username, passwordEncoder.encode(this.password), this.nickname);
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
