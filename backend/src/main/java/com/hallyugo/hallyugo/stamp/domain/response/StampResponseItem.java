package com.hallyugo.hallyugo.stamp.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hallyugo.hallyugo.content.domain.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StampResponseItem {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("title")
    private String title;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}