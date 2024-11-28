package com.hallyugo.hallyugo.stamp.repository;

import com.hallyugo.hallyugo.stamp.domain.Stamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StampRepository extends JpaRepository<Stamp, Long> {
    List<Stamp> findByUserId(Long userId);

    List<Stamp> findByUserIdAndLocationId(Long userId, Long locationId);
}
