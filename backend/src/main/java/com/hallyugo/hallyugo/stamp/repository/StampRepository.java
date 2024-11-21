package com.hallyugo.hallyugo.stamp.repository;

import com.hallyugo.hallyugo.stamp.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StampRepository extends JpaRepository<Stamp, Long> {
    List<Stamp> findByUserId(Long userId);
}
