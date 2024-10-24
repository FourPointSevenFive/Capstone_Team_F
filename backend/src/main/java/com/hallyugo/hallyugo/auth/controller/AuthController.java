package com.hallyugo.hallyugo.auth.controller;

import com.hallyugo.hallyugo.auth.domain.UserToken;
import com.hallyugo.hallyugo.auth.domain.request.TokenRequestDto;
import com.hallyugo.hallyugo.auth.service.AuthService;
import com.hallyugo.hallyugo.user.domain.request.UserRequestDto;
import com.hallyugo.hallyugo.user.domain.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<UserResponseDto> signin(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.signin(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserToken> login(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.login(userRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<UserToken> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody TokenRequestDto tokenRequestDto) {
        authService.logout(tokenRequestDto);
        return ResponseEntity.noContent().build();
    }

}
