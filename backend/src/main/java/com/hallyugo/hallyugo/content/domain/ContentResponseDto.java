package com.hallyugo.hallyugo.content.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentResponseDto {

    @JsonProperty
    private Long id;

    @JsonProperty
    private Category category;

    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty("content_image_url")
    private String contentImageUrl;

    private ContentResponseDto(Content content) {
        this.id = content.getId();
        this.category = content.getCategory();
        this.title = content.getTitle();
        this.description = content.getDescription();
        this.contentImageUrl = content.getContentImageUrl();
    }

    public static ContentResponseDto toDto(Content content) {
        return new ContentResponseDto(content);
    }
}
