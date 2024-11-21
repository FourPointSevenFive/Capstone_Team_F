package com.hallyugo.hallyugo.stamp.service;

import com.hallyugo.hallyugo.stamp.domain.response.StampResponseDto;
import com.hallyugo.hallyugo.stamp.repository.StampRepository;
import com.hallyugo.hallyugo.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
public class StampServiceTest {

    @Autowired
    private StampService stampService;

    @Autowired
    private StampRepository stampRepository;

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
        User user = new User("test", "test", "test" );  // 스탬프가 없는 사용자 생성

        // When
        StampResponseDto response = stampService.getStampsByUser(user, 10);  // limit 10

        // Then
        assertEquals(0, response.getTotal());  // 전체 스탬프 수는 0
        assertEquals(0, response.getStamps().size());  // 반환된 스탬프 개수는 0
    }
}
