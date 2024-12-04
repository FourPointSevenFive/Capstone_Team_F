package com.hallyugo.hallyugo.stamp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hallyugo.hallyugo.stamp.domain.response.StampResponseDto;
import com.hallyugo.hallyugo.stamp.repository.StampRepository;
import com.hallyugo.hallyugo.user.domain.User;
import com.hallyugo.hallyugo.user.repository.UserRepository;
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
public class StampServiceTest {

    @Autowired
    private StampService stampService;

    @Autowired
    private StampRepository stampRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetStampsByUser_withLimitGreaterThanAvailableStamps() {
        // Given
        User user = stampRepository.findAll().get(0).getUser();  // 데이터에서 첫 번째 사용자 가져오기

        // When
        StampResponseDto response = stampService.getStampsByUser(user, 10);  // limit 10

        // Then
        assertEquals(12, response.getTotal());
        assertEquals(10, response.getStamps().size());
    }

    @Test
    public void testGetStampsByUser_withLimitLessThanAvailableStamps() {
        // Given
        User user = stampRepository.findAll().get(0).getUser();  // 데이터에서 첫 번째 사용자 가져오기

        // When
        StampResponseDto response = stampService.getStampsByUser(user, 2);  // limit 2

        // Then
        assertEquals(12, response.getTotal());
        assertEquals(2, response.getStamps().size());
    }

    @Test
    public void testGetStampsByUser_noStamps() {
        // Given
        User user = new User("test", "test", "test");  // 스탬프가 없는 사용자 생성

        // When
        StampResponseDto response = stampService.getStampsByUser(user, 10);  // limit 10

        // Then
        assertEquals(0, response.getTotal());  // 전체 스탬프 수는 0
        assertEquals(0, response.getStamps().size());  // 반환된 스탬프 개수는 0
    }

    @DisplayName("사용자가 특정 위치에서 얻은 스탬프를 조회할 수 있어야 한다.")
    @Test
    public void 위치_스탬프_조회_성공_테스트() {
        // given
        Long userId = 1L;
        Long locationId = 3L;
        User user = userRepository.findById(userId).get();

        // when
        StampResponseDto result = stampService.getStampsByUserAndLocation(user, locationId);

        // then
        assertEquals(1, result.getTotal());
        assertEquals(1, result.getStamps().size());
    }
}
