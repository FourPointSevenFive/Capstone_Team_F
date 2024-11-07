package com.hallyugo.hallyugo.content.repository;

import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.content.domain.Content;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@DataJpaTest
class ContentRepositoryTest {
    private static final int PAGE_NUMBER = 0;
    private static final int INITIAL_CONTENTS_SIZE_PER_CATEGORY = 2;

    @Autowired
    private ContentRepository contentRepository;

    @BeforeAll
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

    @DisplayName("주어진 카테고리에 속하는 2개의 랜덤 콘텐츠가 조회되어야 한다.")
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
}
