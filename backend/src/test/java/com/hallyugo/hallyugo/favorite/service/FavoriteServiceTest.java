package com.hallyugo.hallyugo.favorite.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseDto;
import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseItem;
import com.hallyugo.hallyugo.location.domain.Location;
import com.hallyugo.hallyugo.location.repository.LocationRepository;
import com.hallyugo.hallyugo.user.domain.User;
import com.hallyugo.hallyugo.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
public class FavoriteServiceTest {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

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

    @DisplayName("좋아요 버튼이 눌리지 않은 상태에서 버튼을 누르면 좋아요 개수가 1 증가한다.")
    @Test
    void 좋아요_버튼_클릭_시_좋아요_개수_1_증가_테스트() {
        // given
        Long userId = 1L;
        Long locationId = 3L;
        User user = userRepository.findById(userId).get();
        Location location = locationRepository.findById(locationId).get();
        Long initialFavoriteCount = location.getFavoriteCount();

        // when
        favoriteService.increaseFavoriteCountAndSave(user, locationId);

        // then
        Assertions.assertThat(initialFavoriteCount + 1).isEqualTo(
                locationRepository.findById(locationId).get().getFavoriteCount()
        );
    }

    @DisplayName("좋아요 버튼이 눌린 상태에서 버튼을 누르면 좋아요 개수가 그대로 유지된다.")
    @Test
    void 좋아요_버튼_클릭_시_좋아요_개수_유지_테스트() {
        // given
        Long userId = 1L;
        Long locationId = 1L;
        User user = userRepository.findById(userId).get();
        Location location = locationRepository.findById(locationId).get();
        Long initialFavoriteCount = location.getFavoriteCount();

        // when
        favoriteService.increaseFavoriteCountAndSave(user, locationId);

        // then
        Assertions.assertThat(initialFavoriteCount).isEqualTo(
                locationRepository.findById(locationId).get().getFavoriteCount()
        );
    }

    @DisplayName("좋아요 버튼이 눌린 상태에서 버튼을 취소하면 좋아요 개수가 1 감소한다.")
    @Test
    void 좋아요_버튼_언클릭_시_좋아요_개수_1_감소_테스트() {
        // given
        Long userId = 1L;
        Long locationId = 1L;
        User user = userRepository.findById(userId).get();
        Location location = locationRepository.findById(locationId).get();
        Long initialFavoriteCount = location.getFavoriteCount();

        // when
        favoriteService.decreaseFavoriteCountAndDelete(user, locationId);

        // then
        Assertions.assertThat(initialFavoriteCount - 1).isEqualTo(
                locationRepository.findById(locationId).get().getFavoriteCount()
        );
    }

    @DisplayName("좋아요 버튼이 눌리지 않은 상태에서 버튼을 취소하면 좋아요 개수가 그대로 유지된다.")
    @Test
    void 좋아요_버튼_언클릭_시_좋아요_개수_유지_테스트() {
        // given
        Long userId = 1L;
        Long locationId = 3L;
        User user = userRepository.findById(userId).get();
        Location location = locationRepository.findById(locationId).get();
        Long initialFavoriteCount = location.getFavoriteCount();

        // when
        favoriteService.decreaseFavoriteCountAndDelete(user, locationId);

        // then
        Assertions.assertThat(initialFavoriteCount).isEqualTo(
                locationRepository.findById(locationId).get().getFavoriteCount()
        );
    }
}