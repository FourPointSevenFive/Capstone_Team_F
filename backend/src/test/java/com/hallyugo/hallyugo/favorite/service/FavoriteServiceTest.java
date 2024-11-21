package com.hallyugo.hallyugo.favorite.service;

import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseDto;
import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseItem;
import com.hallyugo.hallyugo.user.domain.User;
import com.hallyugo.hallyugo.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@Transactional
@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
public class FavoriteServiceTest {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("사용자의 즐겨찾기 조회 - 제한 없음")
    @Test
    void getFavoritesByUserTest_noLimit() {
        // given
        Optional<User> optionalUser = userRepository.findById(1L);
        User testUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found"));

        // when
        FavoriteResponseDto response = favoriteService.getFavoritesByUser(testUser, Integer.MAX_VALUE);

        // then
        assertThat(response.getTotal()).isGreaterThan(0); // 즐겨찾기 데이터가 있는지 확인
        assertThat(response.getFavorites().size()).isEqualTo(response.getTotal()); // 전체 즐겨찾기 반환
    }

    @DisplayName("사용자의 즐겨찾기 조회 - 제한 적용")
    @Test
    void getFavoritesByUserTest_withLimit() {
        // given
        Optional<User> optionalUser = userRepository.findById(1L);
        User testUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found"));
        int limit = 2;

        // when
        FavoriteResponseDto response = favoriteService.getFavoritesByUser(testUser, limit);

        // then
        assertThat(response.getFavorites().size()).isEqualTo(limit); // 제한에 따른 크기 확인
    }

    @DisplayName("즐겨찾기 데이터의 상세 정보 확인")
    @Test
    void getFavoriteDetailsTest() {
        // given
        Optional<User> optionalUser = userRepository.findById(1L);
        User testUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found"));

        // when
        FavoriteResponseDto response = favoriteService.getFavoritesByUser(testUser, Integer.MAX_VALUE);
        List<FavoriteResponseItem> items = response.getFavorites();

        // then
        for (FavoriteResponseItem item : items) {
            assertThat(item.getTitle()).isNotEmpty();
            assertThat(item.getDescription()).isNotEmpty();
            assertThat(item.getImage()).isNotEmpty();
        }
    }
}