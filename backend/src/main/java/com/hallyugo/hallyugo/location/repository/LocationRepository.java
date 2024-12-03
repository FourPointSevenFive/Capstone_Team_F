package com.hallyugo.hallyugo.location.repository;

import com.hallyugo.hallyugo.location.domain.Location;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByContentId(Long contentId);
}