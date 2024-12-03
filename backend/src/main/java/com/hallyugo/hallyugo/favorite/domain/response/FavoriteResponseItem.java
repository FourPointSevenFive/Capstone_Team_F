package com.hallyugo.hallyugo.favorite.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoriteResponseItem {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image")
    private String image;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
