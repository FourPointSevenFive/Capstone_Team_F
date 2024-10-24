package com.hallyugo.hallyugo.image.domain;

import com.hallyugo.hallyugo.location.domain.Location;
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
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "image")
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "image_url")
    private String imageUrl;

    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    public Image(Location location, String imageUrl, String description) {
        this.location = location;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public void setLocation(Location location) {
        if (this.location != null) {
            this.location.getImages().remove(this);
        }

        this.location = location;
        location.getImages().add(this);
    }
}