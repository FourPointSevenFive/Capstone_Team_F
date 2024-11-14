package com.hallyugo.hallyugo.proof.repository;

import com.hallyugo.hallyugo.proof.domain.ProofShot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProofShotRepository extends JpaRepository<ProofShot, Long> {
    List<ProofShot> findByUserId(Long userId);
}
