package com.hallyugo.hallyugo.location.domain;

import com.hallyugo.hallyugo.content.domain.Content;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "location")
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    private String title;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String description;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "favorite_cnt")
    @ColumnDefault("0")
    private Long favoriteCount;

    private String pose;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Location(String title, BigDecimal latitude, BigDecimal longitude, String description,
                    String videoLink, Long favoriteCount, String pose) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.videoLink = videoLink;
        this.favoriteCount = favoriteCount;
        this.pose = pose;
    }

    public void setContent(Content content) {
        if (this.content != null) {
            this.content.getLocations().remove(this);
        }

        this.content = content;
        content.getLocations().add(this);
    }

    public void increaseFavoriteCount() {
        this.favoriteCount++;
    }
}