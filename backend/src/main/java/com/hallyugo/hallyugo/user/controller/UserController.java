package com.hallyugo.hallyugo.user.controller;

import com.hallyugo.hallyugo.auth.annotation.AuthUser;
import com.hallyugo.hallyugo.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthUser User user) {
        log.info("{}", user.getUsername());
        return ResponseEntity.ok(user);
    }
}
