package com.hallyugo.hallyugo.image.service;

import com.hallyugo.hallyugo.image.domain.response.ImageResponseDto;
import com.hallyugo.hallyugo.image.repository.ImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;
    
    public List<ImageResponseDto> getImagesByLocationId(Long locationId) {
        return imageRepository.findByLocationId(locationId).stream()
                .map(ImageResponseDto::toDto).toList();
    }
}
