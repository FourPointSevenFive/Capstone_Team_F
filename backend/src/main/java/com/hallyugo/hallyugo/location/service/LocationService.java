package com.hallyugo.hallyugo.location.service;

import com.hallyugo.hallyugo.image.domain.response.ImageResponseDto;
import com.hallyugo.hallyugo.image.service.ImageService;
import com.hallyugo.hallyugo.location.domain.response.LocationWithImagesResponseDto;
import com.hallyugo.hallyugo.location.repository.LocationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final ImageService imageService;

    public List<LocationWithImagesResponseDto> getLocationsWithImagesByContentId(Long contentId) {
        return locationRepository.findByContentId(contentId).stream()
                .map(location -> {
                    List<ImageResponseDto> images = imageService.getImagesByLocationId(location.getId());
                    return LocationWithImagesResponseDto.toDto(location, images);
                })
                .toList();
    }

}
