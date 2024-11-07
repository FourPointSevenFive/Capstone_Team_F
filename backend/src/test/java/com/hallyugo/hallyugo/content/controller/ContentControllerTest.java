package com.hallyugo.hallyugo.content.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.content.domain.Content;
import com.hallyugo.hallyugo.content.domain.ContentResponseDto;
import com.hallyugo.hallyugo.content.service.ContentService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(controllers = ContentController.class)
class ContentControllerTest {
    private static final String BASE_URL = "/api/v1/content";
    private static final String INITIAL_CONTENTS_PATH = BASE_URL + "/initial";
    private static final int INITIAL_CONTENTS_SIZE_PER_CATEGORY = 2;
    private Map<String, List<ContentResponseDto>> result;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @BeforeEach
    void setUp() {
        result = new HashMap<>();
        List<Content> dramaContents = new ArrayList<>();
        List<Content> kpopContents = new ArrayList<>();
        List<Content> movieContents = new ArrayList<>();
        List<Content> novelContents = new ArrayList<>();

        dramaContents.add(new Content(Category.DRAMA, "dramaTitle1", "dramaDesc1", "dramaUrl1"));
        dramaContents.add(new Content(Category.DRAMA, "dramaTitle2", "dramaDesc2", "dramaUrl2"));
        kpopContents.add(new Content(Category.K_POP, "kpopTitle1", "kpopDesc1", "kpopUrl1"));
        kpopContents.add(new Content(Category.K_POP, "kpopTitle2", "kpopDesc2", "kpopUrl2"));
        movieContents.add(new Content(Category.MOVIE, "movieTitle1", "movieDesc1", "movieUrl1"));
        movieContents.add(new Content(Category.MOVIE, "movieTitle2", "movieDesc2", "movieUrl2"));
        novelContents.add(new Content(Category.NOVEL, "novelTitle1", "novelDesc1", "novelUrl1"));
        novelContents.add(new Content(Category.NOVEL, "novelTitle2", "novelDesc2", "novelUrl2"));

        List<ContentResponseDto> dramaContentDtos = dramaContents.stream().map(ContentResponseDto::toDto).toList();
        List<ContentResponseDto> kpopContentDtos = kpopContents.stream().map(ContentResponseDto::toDto).toList();
        List<ContentResponseDto> movieContentDtos = movieContents.stream().map(ContentResponseDto::toDto).toList();
        List<ContentResponseDto> novelContentDtos = novelContents.stream().map(ContentResponseDto::toDto).toList();

        result.put(Category.DRAMA.name(), dramaContentDtos);
        result.put(Category.K_POP.name(), kpopContentDtos);
        result.put(Category.MOVIE.name(), movieContentDtos);
        result.put(Category.NOVEL.name(), novelContentDtos);
    }

    @DisplayName("카테고리별 랜덤 콘텐츠 조회 시 정상 결과가 반환되어야 한다.")
    @WithMockUser("user") // to avoid 401 error
    @Test
    void 카테고리별_랜덤_콘텐츠_조회_성공_테스트() throws Exception {
        // given
        when(contentService.getRandomContentsByCategory()).thenReturn(result);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(INITIAL_CONTENTS_PATH)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.DRAMA.length()").value(INITIAL_CONTENTS_SIZE_PER_CATEGORY))
                .andExpect(jsonPath("$.K_POP.length()").value(INITIAL_CONTENTS_SIZE_PER_CATEGORY))
                .andExpect(jsonPath("$.MOVIE.length()").value(INITIAL_CONTENTS_SIZE_PER_CATEGORY))
                .andExpect(jsonPath("$.NOVEL.length()").value(INITIAL_CONTENTS_SIZE_PER_CATEGORY))
                .andDo(print());
    }
}
