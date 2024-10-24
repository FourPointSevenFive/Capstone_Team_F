package com.hallyugo.hallyugo.location.repository;

import com.hallyugo.hallyugo.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
