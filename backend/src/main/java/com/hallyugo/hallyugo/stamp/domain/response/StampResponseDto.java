package com.hallyugo.hallyugo.stamp.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StampResponseDto {

    @JsonProperty("total")
    private int total;

    @JsonProperty("stamps")
    private List<StampResponseItem> stamps;
}