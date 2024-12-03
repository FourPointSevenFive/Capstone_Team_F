package com.hallyugo.hallyugo.content.controller;

import com.hallyugo.hallyugo.content.domain.response.ContentForMapResponseDto;
import com.hallyugo.hallyugo.content.domain.response.ContentResponseDto;
import com.hallyugo.hallyugo.content.service.ContentService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/content/initial")
    public ResponseEntity<Map<String, List<ContentResponseDto>>> getRandomContents() {
        Map<String, List<ContentResponseDto>> result = contentService.getRandomContents();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/content", params = "category")
    public ResponseEntity<List<ContentResponseDto>> getContentsByCategory(@RequestParam String category) {
        List<ContentResponseDto> result = contentService.getContentsByCategory(category);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/content", params = "keyword")
    public ResponseEntity<List<ContentResponseDto>> getContentsByKeyword(@RequestParam String keyword) {
        List<ContentResponseDto> result = contentService.getContentsByKeyword(keyword);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/location", params = "content_id")
    public ResponseEntity<ContentForMapResponseDto> getContentWithLocationsAndImages(
            @RequestParam(name = "content_id") Long contentId
    ) {
        ContentForMapResponseDto result = contentService.getContentWithLocationsAndImages(contentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/location", params = "category")
    public ResponseEntity<List<ContentForMapResponseDto>> getContentsWithLocationsAndImagesByCategory(
            @RequestParam(name = "category") String category
    ) {
        List<ContentForMapResponseDto> result = contentService.getContentsWithLocationsAndImagesByCategory(category);
        return ResponseEntity.ok(result);
    }
}