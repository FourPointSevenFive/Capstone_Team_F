package com.hallyugo.hallyugo.favorite.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hallyugo.hallyugo.favorite.domain.EntityType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FavoriteResponseItem {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("entity_id")
    private Long entityId;

    @JsonProperty("entity_type")
    private EntityType entityType;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image")
    private String image;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
