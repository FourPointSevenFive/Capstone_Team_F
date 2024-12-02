package com.hallyugo.hallyugo.content.controller;

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
@RequestMapping("/api/v1/content")
@RestController
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/initial")
    public ResponseEntity<Map<String, List<ContentResponseDto>>> getRandomContents() {
        Map<String, List<ContentResponseDto>> result = contentService.getRandomContents();
        return ResponseEntity.ok(result);
    }

    @GetMapping(params = "category")
    public ResponseEntity<List<ContentResponseDto>> getContentsByCategory(@RequestParam String category) {
        List<ContentResponseDto> result = contentService.getContentsByCategory(category);
        return ResponseEntity.ok(result);
    }

    @GetMapping(params = "keyword")
    public ResponseEntity<List<ContentResponseDto>> getContentsByKeyword(@RequestParam String keyword) {
        List<ContentResponseDto> result = contentService.getContentsByKeyword(keyword);
        return ResponseEntity.ok(result);
    }
}