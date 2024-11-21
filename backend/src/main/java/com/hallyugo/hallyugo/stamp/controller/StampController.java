package com.hallyugo.hallyugo.stamp.controller;

import com.hallyugo.hallyugo.auth.annotation.AuthUser;
import com.hallyugo.hallyugo.stamp.domain.response.StampResponseDto;
import com.hallyugo.hallyugo.stamp.service.StampService;
import com.hallyugo.hallyugo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class StampController {

    private final StampService stampService;

    @GetMapping("/stamp")
    public ResponseEntity<StampResponseDto> getUserStamp(
            @AuthUser User user,
            @RequestParam(name = "limit", defaultValue = "9") int limit
    ) {
        StampResponseDto result = stampService.getStampsByUser(user, limit);
        return ResponseEntity.ok(result);
    }
}
