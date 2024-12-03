package com.hallyugo.hallyugo.location.service;

import com.hallyugo.hallyugo.content.domain.Content;
import com.hallyugo.hallyugo.content.repository.ContentRepository;
import com.hallyugo.hallyugo.image.domain.response.ImageResponseDto;
import com.hallyugo.hallyugo.image.repository.ImageRepository;
import com.hallyugo.hallyugo.image.service.ImageService;
import com.hallyugo.hallyugo.location.domain.Location;
import com.hallyugo.hallyugo.location.domain.response.LocationWithImagesResponseDto;
import com.hallyugo.hallyugo.location.repository.LocationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationService {
    private final ContentRepository contentRepository;
    private final LocationRepository locationRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    public List<LocationWithImagesResponseDto> getLocationsWithImagesByContentId(Long contentId) {
        return locationRepository.findByContentId(contentId).stream()
                .map(location -> {
                    List<ImageResponseDto> images = imageService.getImagesByLocationId(location.getId());
                    return LocationWithImagesResponseDto.toDto(location, images);
                })
                .toList();
    }

    public List<LocationWithImagesResponseDto> getLocationsWithImagesByKeyword(String keyword) {
        List<Content> contents = contentRepository.findByTitleContainingIgnoreCase(keyword);
        List<Location> locations = locationRepository.findByTitleContainingIgnoreCase(keyword);

        // Content title에 keyword가 포함된 경우
        List<LocationWithImagesResponseDto> locationsWithImages = contents.stream()
                .flatMap(content -> locationRepository.findByContentId(content.getId()).stream()
                        .map(location -> {
                            List<ImageResponseDto> images = imageService.getImagesByLocationId(location.getId());
                            return LocationWithImagesResponseDto.toDto(location, images);
                        }))
                .collect(Collectors.toList());

        // Location title에 keyword가 포함된 경우
        locationsWithImages.addAll(locations.stream()
                .map(location -> {
                    List<ImageResponseDto> images = imageService.getImagesByLocationId(location.getId());
                    return LocationWithImagesResponseDto.toDto(location, images);
                }).collect(Collectors.toList()));

        // 중복 DTO 제거
        List<LocationWithImagesResponseDto> uniqueLocationsWithImages = locationsWithImages.stream()
                .distinct().collect(Collectors.toList());

        return uniqueLocationsWithImages;
    }

}
