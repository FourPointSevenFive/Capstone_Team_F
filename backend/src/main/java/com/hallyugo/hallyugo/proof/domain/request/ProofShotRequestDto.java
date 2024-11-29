package com.hallyugo.hallyugo.proof.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProofShotRequestDto {

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("description")
    private String description;
}
