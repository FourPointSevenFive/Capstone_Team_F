package com.hallyugo.hallyugo.content.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.content.domain.Content;
import com.hallyugo.hallyugo.location.domain.response.LocationWithImagesResponseDto;
import java.util.List;
import lombok.Getter;

public class ContentForMapResponseDto {

    @JsonProperty
    private Long id;

    @JsonProperty
    private Category category;

    @JsonProperty
    private String title;

    @JsonProperty
    private String hashtag;

    @JsonProperty
    @Getter
    private List<LocationWithImagesResponseDto> locations;

    private ContentForMapResponseDto(Content content, List<LocationWithImagesResponseDto> locations) {
        this.id = content.getId();
        this.category = content.getCategory();
        this.title = content.getTitle();
        this.hashtag = content.getHashtag();
        this.locations = locations;
    }

    public static ContentForMapResponseDto toDto(Content content,
                                                 List<LocationWithImagesResponseDto> locationWithImages) {
        return new ContentForMapResponseDto(content, locationWithImages);
    }

}
