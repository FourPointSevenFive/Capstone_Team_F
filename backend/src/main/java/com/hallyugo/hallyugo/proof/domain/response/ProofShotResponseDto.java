package com.hallyugo.hallyugo.proof.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProofShotResponseDto {

    @JsonProperty("total")
    private int total;

    @JsonProperty("proof_shots")
    private List<ProofShotResponseItem> proofShots;
}
