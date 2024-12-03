package com.hallyugo.hallyugo.location.controller;

import com.hallyugo.hallyugo.location.domain.response.LocationWithImagesResponseDto;
import com.hallyugo.hallyugo.location.service.LocationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/location")
@RestController
public class LocationController {
    private final LocationService locationService;

    @GetMapping(params = "keyword")
    public ResponseEntity<List<LocationWithImagesResponseDto>> searchLocationsWithImagesByKeyword(
            @RequestParam(name = "keyword") String keyword
    ) {
        List<LocationWithImagesResponseDto> result = locationService.getLocationsWithImagesByKeyword(keyword);
        return ResponseEntity.ok(result);
    }
}
