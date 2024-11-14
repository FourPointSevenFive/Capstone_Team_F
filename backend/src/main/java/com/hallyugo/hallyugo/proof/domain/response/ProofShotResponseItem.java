package com.hallyugo.hallyugo.proof.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hallyugo.hallyugo.content.domain.Category;
import com.hallyugo.hallyugo.proof.domain.ProofShot;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProofShotResponseItem {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image")
    private String image;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private ProofShotResponseItem(ProofShot proofShot) {
        this.id = proofShot.getId();
        this.locationId = proofShot.getLocation().getId();
        this.category = proofShot.getLocation().getContent().getCategory();
        this.title = proofShot.getLocation().getTitle();
        this.description = proofShot.getDescription();
        this.image = proofShot.getImageUrl();
        this.createdAt = proofShot.getCreatedAt();
    }

    public static ProofShotResponseItem toDto(ProofShot proofShot) {
        return new ProofShotResponseItem(proofShot);
    }
}
