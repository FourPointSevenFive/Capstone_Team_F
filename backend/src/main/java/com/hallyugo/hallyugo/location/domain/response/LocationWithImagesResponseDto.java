package com.hallyugo.hallyugo.location.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hallyugo.hallyugo.image.domain.Image;
import com.hallyugo.hallyugo.image.domain.response.ImageResponseDto;
import com.hallyugo.hallyugo.location.domain.Location;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class LocationWithImagesResponseDto {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private BigDecimal latitude;

    @JsonProperty
    private BigDecimal longitude;

    @JsonProperty
    private String description;

    @JsonProperty("video_link")
    private String videoLink;

    @JsonProperty("favorite_cnt")
    private Long favoriteCount;

    @JsonProperty
    private String pose;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty
    @Getter
    private List<ImageResponseDto> images = new ArrayList<>();

    private LocationWithImagesResponseDto(Location location, List<ImageResponseDto> imageResponseDtos) {
        this.id = location.getId();
        this.title = location.getTitle();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.description = location.getDescription();
        this.videoLink = location.getVideoLink();
        this.favoriteCount = location.getFavoriteCount();
        this.pose = location.getPose();
        this.createdAt = location.getCreatedAt();
        this.updatedAt = location.getUpdatedAt();
        this.images = imageResponseDtos;
    }

    public static LocationWithImagesResponseDto toDto(Location location, List<Image> images) {
        List<ImageResponseDto> imageResponseDtos = images.stream()
                .map(ImageResponseDto::toDto)
                .collect(Collectors.toList());

        return new LocationWithImagesResponseDto(location, imageResponseDtos);
    }

}
