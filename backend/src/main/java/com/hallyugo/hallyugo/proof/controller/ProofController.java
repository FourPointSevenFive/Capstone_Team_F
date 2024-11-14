package com.hallyugo.hallyugo.proof.controller;

import com.hallyugo.hallyugo.auth.annotation.AuthUser;
import com.hallyugo.hallyugo.proof.domain.response.ProofShotResponseDto;
import com.hallyugo.hallyugo.proof.service.ProofShotService;
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
public class ProofController {

    private final ProofShotService proofShotService;

    @GetMapping("/proof-shot")
    public ResponseEntity<ProofShotResponseDto> getUserProofShot(
            @AuthUser User user,
            @RequestParam(name = "limit", defaultValue = "9") int limit
    ) {
        ProofShotResponseDto result = proofShotService.getProofShotsByUser(user, limit);
        return ResponseEntity.ok(result);
    }
}
