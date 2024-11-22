package com.hallyugo.hallyugo.image.repository;

import com.hallyugo.hallyugo.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByLocationId(Long locationId);
}
