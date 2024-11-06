package com.hallyugo.hallyugo.content.domain;

public class ContentResponseDto {
    private Long id;
    private Category category;
    private String title;
    private String description;
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
