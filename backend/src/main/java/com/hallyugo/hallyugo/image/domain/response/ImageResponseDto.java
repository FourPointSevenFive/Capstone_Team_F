package com.hallyugo.hallyugo.image.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hallyugo.hallyugo.image.domain.Image;
import java.time.LocalDateTime;
import java.util.Objects;

public class ImageResponseDto {

    @JsonProperty
    private Long id;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty
    private String description;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private ImageResponseDto(Image image) {
        this.id = image.getId();
        this.imageUrl = image.getImageUrl();
        this.description = image.getDescription();
        this.createdAt = image.getCreatedAt();
    }

    public static ImageResponseDto toDto(Image image) {
        return new ImageResponseDto(image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ImageResponseDto that = (ImageResponseDto) obj;

        return Objects.equals(id, that.id);
    }

}
