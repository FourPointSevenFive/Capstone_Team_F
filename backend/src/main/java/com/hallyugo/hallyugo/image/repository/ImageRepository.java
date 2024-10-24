package com.hallyugo.hallyugo.image.repository;

import com.hallyugo.hallyugo.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
