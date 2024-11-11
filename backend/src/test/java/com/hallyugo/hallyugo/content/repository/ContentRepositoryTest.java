package com.hallyugo.hallyugo.content.repository;

import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.content.domain.Content;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class ContentRepositoryTest {
    private static final int PAGE_NUMBER = 0;
    private static final int INITIAL_CONTENTS_SIZE_PER_CATEGORY = 2;
    private static final int TOTAL_CONTENTS_SIZE_PER_CATEGORY = 3;

    @Autowired
    private ContentRepository contentRepository;

    @BeforeEach
    void setUp() {
        contentRepository.saveAll(createContents());
    }

    @DisplayName("주어진 카테고리에 해당하는 2개의 랜덤 콘텐츠가 조회되어야 한다.")
    @ParameterizedTest
    @EnumSource(Category.class)
    void 카테고리별_랜덤_콘텐츠_조회_성공_테스트(Category category) {
        // when
        List<Content> fetchedContents = contentRepository.findRandomContentsByCategory(category,
                PageRequest.of(PAGE_NUMBER, INITIAL_CONTENTS_SIZE_PER_CATEGORY));

        // then
        Assertions.assertThat(fetchedContents).hasSize(INITIAL_CONTENTS_SIZE_PER_CATEGORY)
                .extracting("category").containsExactly(category, category);
    }

    @DisplayName("주어진 카테고리에 해당하는 전체 콘텐츠를 조회할 수 있어야 한다.")
    @ParameterizedTest
    @EnumSource(Category.class)
    void 카테고리별_전체_콘텐츠_조회_성공_테스트(Category category) {
        // when
        List<Content> fetchedContents = contentRepository.findByCategory(category);

        // then
        Assertions.assertThat(fetchedContents).hasSize(TOTAL_CONTENTS_SIZE_PER_CATEGORY);
    }

    @DisplayName("제목에 키워드가 포함된 전체 콘텐츠를 조회할 수 있어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"DraMa", "drama", "Drama", "DRAMA"})
    void 키워드_검색_성공_테스트(String keyword) {
        // when
        List<Content> fetchedContents = contentRepository.findByTitleContainingIgnoreCase(keyword);

        // then
        Assertions.assertThat(fetchedContents).hasSize(TOTAL_CONTENTS_SIZE_PER_CATEGORY);
    }

    @DisplayName("제목에 키워드가 포함된 콘텐츠가 없는 경우 빈 리스트가 반환되어야 한다.")
    @Test
    void 키워드_검색_실패_테스트() {
        // given
        String nonMatchingKeyword = "nonMatchingKeyword";

        // when
        List<Content> fetchedContents = contentRepository.findByTitleContainingIgnoreCase(nonMatchingKeyword);

        // then
        Assertions.assertThat(fetchedContents).isEmpty();
    }

    private List<Content> createContents() {
        List<Content> contents = new ArrayList<>();

        for (Category category : Category.values()) {
            contents.addAll(createContentsByCategory(category));
        }

        return contents;
    }

    private List<Content> createContentsByCategory(Category category) {
        List<Content> contentsByCategory = new ArrayList<>();

        for (int i = 1; i <= TOTAL_CONTENTS_SIZE_PER_CATEGORY; i++) {
            String title = category.name() + "Title" + i;
            String description = category.name() + "Desc" + i;
            String url = category.name() + "Url" + i;
            String hashtag = "#" + category.name() + i;
            contentsByCategory.add(new Content(category, title, description, url, hashtag));
        }

        return contentsByCategory;
    }
}
