package com.hallyugo.hallyugo.proof.repository;

import com.hallyugo.hallyugo.proof.domain.Proof;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProofRepository extends JpaRepository<Proof, Long> {
}
