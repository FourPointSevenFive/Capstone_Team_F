package com.hallyugo.hallyugo.proof.domain;

import com.hallyugo.hallyugo.location.domain.Location;
import com.hallyugo.hallyugo.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "proof_shot")
public class Proof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    private String description;

    @Column(name = "created_at", columnDefinition = "DATETIME(6)")
    @CreatedDate
    private LocalDateTime createdAt;

    public Proof(User user, Location location, String imageUrl, String description) {
        this.user = user;
        this.location = location;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
