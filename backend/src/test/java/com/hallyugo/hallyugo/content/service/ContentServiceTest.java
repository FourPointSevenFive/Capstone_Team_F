package com.hallyugo.hallyugo.content.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hallyugo.hallyugo.common.exception.EntityNotFoundException;
import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.content.domain.Content;
import com.hallyugo.hallyugo.content.domain.ContentResponseDto;
import com.hallyugo.hallyugo.content.repository.ContentRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class ContentServiceTest {
    private static final int CATEGORY_COUNT = 4;
    private static final int INITIAL_CONTENTS_SIZE_PER_CATEGORY = 2;
    private static final int TOTAL_CONTENTS_SIZE_PER_CATEGORY = 3;

    @MockBean
    private RedissonClient redissonClient;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentService contentService;

    @BeforeEach
    void setUp() {
        List<Content> contents = new ArrayList<>();
        contents.add(new Content(Category.DRAMA, "dramaTitle1", "dramaDesc1", "dramaUrl1"));
        contents.add(new Content(Category.DRAMA, "dramaTitle2", "dramaDesc2", "dramaUrl2"));
        contents.add(new Content(Category.DRAMA, "dramaTitle3", "dramaDesc3", "dramaUrl3"));
        contents.add(new Content(Category.K_POP, "kpopTitle1", "kpopDesc1", "kpopUrl1"));
        contents.add(new Content(Category.K_POP, "kpopTitle2", "kpopDesc2", "kpopUrl2"));
        contents.add(new Content(Category.K_POP, "kpopTitle3", "kpopDesc3", "kpopUrl3"));
        contents.add(new Content(Category.MOVIE, "movieTitle1", "movieDesc1", "movieUrl1"));
        contents.add(new Content(Category.MOVIE, "movieTitle2", "movieDesc2", "movieUrl2"));
        contents.add(new Content(Category.MOVIE, "movieTitle3", "movieDesc3", "movieUrl3"));
        contents.add(new Content(Category.NOVEL, "novelTitle1", "novelDesc1", "novelUrl1"));
        contents.add(new Content(Category.NOVEL, "novelTitle2", "novelDesc2", "novelUrl2"));
        contents.add(new Content(Category.NOVEL, "novelTitle3", "novelDesc3", "novelUrl3"));

        contentRepository.saveAll(contents);
    }

    @DisplayName("각 카테고리마다 2개씩 총 8개의 랜덤 콘텐츠가 조회되어야 한다.")
    @Test
    void 카테고리별_랜덤_콘텐츠_조회_성공_테스트() {
        // when
        Map<String, List<ContentResponseDto>> fetchedResult = contentService.getRandomContents();

        // then
        Assertions.assertThat(fetchedResult).hasSize(CATEGORY_COUNT);

        for (Category category : Category.values()) {
            Assertions.assertThat(fetchedResult.get(category.name())).hasSize(INITIAL_CONTENTS_SIZE_PER_CATEGORY);
        }
    }

    @DisplayName("주어진 카테고리에 해당하는 전체 콘텐츠를 조회할 수 있어야 한다.")
    @ParameterizedTest
    @EnumSource(Category.class)
    void 카테고리별_전체_콘텐츠_조회_성공_테스트(Category category) {
        // when
        List<ContentResponseDto> fetchedContents = contentService.getContentsByCategory(category.name());

        // then
        Assertions.assertThat(fetchedContents).hasSize(TOTAL_CONTENTS_SIZE_PER_CATEGORY);
    }

    @DisplayName("제목에 키워드가 포함된 전체 콘텐츠를 조회할 수 있어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"Kpop", "KPOP", "kpop"})
    void 키워드_검색_성공_테스트(String keyword) {
        // when
        List<ContentResponseDto> fetchedContents = contentService.getContentsByKeyword(keyword);

        // then
        Assertions.assertThat(fetchedContents).hasSize(TOTAL_CONTENTS_SIZE_PER_CATEGORY);
    }

    @DisplayName("제목에 키워드가 포함된 콘텐츠가 없는 경우 EntityNotFoundException이 발생해야 한다.")
    @Test
    void 키워드_검색_실패_테스트() {
        // given
        String nonMatchingKeyword = "nonMatchingKeyword";

        // when & then
        assertThrows(EntityNotFoundException.class, () -> contentService.getContentsByKeyword(nonMatchingKeyword));
    }
}