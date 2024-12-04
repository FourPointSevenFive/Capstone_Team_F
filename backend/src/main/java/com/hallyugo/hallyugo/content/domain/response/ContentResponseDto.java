package com.hallyugo.hallyugo.content.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.content.domain.Content;

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

    @JsonProperty
    private String hashtag;

    private ContentResponseDto(Content content) {
        this.id = content.getId();
        this.category = content.getCategory();
        this.title = content.getTitle();
        this.description = content.getDescription();
        this.contentImageUrl = content.getContentImageUrl();
        this.hashtag = content.getHashtag();
    }

    public static ContentResponseDto toDto(Content content) {
        return new ContentResponseDto(content);
    }
}
