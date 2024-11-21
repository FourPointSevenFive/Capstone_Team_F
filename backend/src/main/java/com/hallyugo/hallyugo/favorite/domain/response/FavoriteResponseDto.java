package com.hallyugo.hallyugo.favorite.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FavoriteResponseDto {

    @JsonProperty("total")
    private int total;

    @JsonProperty("favorites")
    List<FavoriteResponseItem> favorites;
}
