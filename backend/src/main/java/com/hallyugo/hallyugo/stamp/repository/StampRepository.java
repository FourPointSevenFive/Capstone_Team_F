package com.hallyugo.hallyugo.stamp.repository;

import com.hallyugo.hallyugo.stamp.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {
}
