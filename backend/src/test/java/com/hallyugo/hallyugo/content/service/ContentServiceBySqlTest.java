package com.hallyugo.hallyugo.content.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hallyugo.hallyugo.content.domain.response.ContentForMapResponseDto;
import com.hallyugo.hallyugo.content.repository.ContentRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Transactional
@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
public class ContentServiceBySqlTest {

    @MockBean
    private RedissonClient redissonClient;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentService contentService;

    @DisplayName("특정 콘텐츠와 연관된 위치와 각 위치에 대한 이미지를 조회할 수 있어야 한다.")
    @Test
    void 콘텐츠_위치_이미지_리스트_조회_성공_테스트() {
        // given
        int size = 10;
        Long contentId = 1L;

        // when
        ContentForMapResponseDto result = contentService.getContentWithLocationsAndImages(contentId);

        // then
        assertEquals(size, result.getLocations().size());
        assertEquals(2, result.getLocations().get(0).getImages().size());
        assertEquals(1, result.getLocations().get(1).getImages().size());
        assertEquals(0, result.getLocations().get(2).getImages().size());
    }

    @DisplayName("카테고리를 이용해 콘텐츠와 연관된 위치와 각 위치에 대한 이미지를 조회할 수 있어야 한다.")
    @Test
    void 카테고리_콘텐츠_위치_이미지_리스트_조회_성공_테스트() {
        // given
        String category = "K_POP";
        int contentSize = 1;
        int locationSize = 10;

        // when
        List<ContentForMapResponseDto> result = contentService.getContentsWithLocationsAndImagesByCategory(category);

        // then
        assertEquals(contentSize, result.size());
        assertEquals(locationSize, result.getFirst().getLocations().size());
        assertEquals(2, result.getFirst().getLocations().get(0).getImages().size());
        assertEquals(1, result.getFirst().getLocations().get(1).getImages().size());
        assertEquals(0, result.getFirst().getLocations().get(2).getImages().size());
    }
}
