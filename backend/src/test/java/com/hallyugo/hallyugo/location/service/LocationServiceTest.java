package com.hallyugo.hallyugo.location.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hallyugo.hallyugo.content.repository.ContentRepository;
import com.hallyugo.hallyugo.image.repository.ImageRepository;
import com.hallyugo.hallyugo.location.domain.response.LocationWithImagesResponseDto;
import com.hallyugo.hallyugo.location.repository.LocationRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Transactional
@ActiveProfiles("test")
@Sql("/data.sql")
@SpringBootTest
public class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ImageRepository imageRepository;

    @DisplayName("키워드로 위치와 그에 대한 이미지를 검색할 수 있어야 한다.")
    @Test
    void 키워드_위치_이미지_검색_성공_테스트() {
        // given
        String keyword = "1";
        int expectedSize = 12;

        // when
        List<LocationWithImagesResponseDto> result =
                locationService.getLocationsWithImagesByKeyword(keyword);

        // then
        assertEquals(expectedSize, result.size());
    }
}