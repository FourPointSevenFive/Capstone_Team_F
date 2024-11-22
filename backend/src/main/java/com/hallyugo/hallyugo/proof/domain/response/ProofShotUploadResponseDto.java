package com.hallyugo.hallyugo.proof.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hallyugo.hallyugo.proof.domain.ProofShot;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProofShotUploadResponseDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private ProofShotUploadResponseDto(ProofShot proofShot) {
        this.id = proofShot.getId();
        this.userId = proofShot.getUser().getId();
        this.locationId = proofShot.getLocation().getId();
        this.imageUrl = proofShot.getImageUrl();
        this.description = proofShot.getDescription();
        this.createdAt = proofShot.getCreatedAt();
    }

    public static ProofShotUploadResponseDto toDto(ProofShot proofShot) {
        return new ProofShotUploadResponseDto(proofShot);
    }
}
