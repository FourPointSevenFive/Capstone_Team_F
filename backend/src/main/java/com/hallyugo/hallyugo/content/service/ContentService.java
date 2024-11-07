package com.hallyugo.hallyugo.content.service;

import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.content.domain.Content;
import com.hallyugo.hallyugo.content.domain.ContentResponseDto;
import com.hallyugo.hallyugo.content.repository.ContentRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContentService {
    private static final int PAGE_NUMBER = 0;
    private static final int INITIAL_CONTENTS_SIZE_PER_CATEGORY = 2;
    private final ContentRepository contentRepository;

    public Map<String, List<ContentResponseDto>> getRandomContentsByCategory() {
        Map<String, List<ContentResponseDto>> result = new HashMap<>();
        Pageable pageable = PageRequest.of(PAGE_NUMBER, INITIAL_CONTENTS_SIZE_PER_CATEGORY);

        for (Category category : Category.values()) {
            List<Content> contents = contentRepository.findRandomContentsByCategory(category, pageable);
            List<ContentResponseDto> contentResponseDtos = contents.stream()
                    .map(ContentResponseDto::toDto)
                    .toList();

            result.put(category.name(), contentResponseDtos);
        }

        return result;
    }

    public List<ContentResponseDto> getContentsByCategory(String category) {
        List<Content> contents = contentRepository.findByCategory(Category.valueOf(category));
        return contents.stream().map(ContentResponseDto::toDto).toList();
    }
}
