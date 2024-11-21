package com.hallyugo.hallyugo.proof.service;

import com.hallyugo.hallyugo.proof.domain.response.ProofShotResponseDto;
import com.hallyugo.hallyugo.proof.domain.response.ProofShotResponseItem;
import com.hallyugo.hallyugo.user.domain.User;
import com.hallyugo.hallyugo.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
public class ProofShotServiceTest {

    @Autowired
    private ProofShotService proofShotService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("사용자의 ProofShot 조회 - 제한 없음")
    @Test
    void getProofShotsByUserTest_noLimit() {
        // given
        User testUser = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // when
        ProofShotResponseDto response = proofShotService.getProofShotsByUser(testUser, Integer.MAX_VALUE);

        // then
        assertThat(response.getTotal()).isGreaterThan(0); // 즐겨찾기 데이터가 있는지 확인
        assertThat(response.getProofShots()).isNotEmpty(); // ProofShot 데이터가 있는지 확인
        assertThat(response.getProofShots().size()).isEqualTo(response.getTotal()); // 전체 ProofShot 반환
    }

    @DisplayName("사용자의 ProofShot 조회 - 제한 적용")
    @Test
    void getProofShotsByUserTest_withLimit() {
        // given
        User testUser = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        int limit = 2;

        // when
        ProofShotResponseDto response = proofShotService.getProofShotsByUser(testUser, limit);

        // then
        assertThat(response.getProofShots().size()).isEqualTo(limit); // 제한에 따른 크기 확인
    }

    @DisplayName("ProofShot 데이터의 상세 정보 확인")
    @Test
    void getProofShotDetailsTest() {
        // given
        User testUser = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // when
        ProofShotResponseDto response = proofShotService.getProofShotsByUser(testUser, Integer.MAX_VALUE);
        List<ProofShotResponseItem> items = response.getProofShots();

        // then
        for (ProofShotResponseItem item : items) {
            assertThat(item.getTitle()).isNotEmpty();
            assertThat(item.getDescription()).isNotEmpty();
            assertThat(item.getImage()).isNotEmpty();
        }
    }
}
