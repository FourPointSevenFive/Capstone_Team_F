package com.hallyugo.hallyugo.proof.service;

import com.hallyugo.hallyugo.proof.domain.ProofShot;
import com.hallyugo.hallyugo.proof.domain.response.ProofShotResponseDto;
import com.hallyugo.hallyugo.proof.domain.response.ProofShotResponseItem;
import com.hallyugo.hallyugo.proof.repository.ProofShotRepository;
import com.hallyugo.hallyugo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProofShotService {

    private final ProofShotRepository proofShotRepository;

    public ProofShotResponseDto getProofShotsByUser(User user, int limit) {
        List<ProofShot> proofShots = proofShotRepository.findByUserId(user.getId());

        List<ProofShot> limitedProofShots = proofShots.size() > limit ? proofShots.subList(0, limit) : proofShots;

        List<ProofShotResponseItem> proofShotResponseItems = limitedProofShots.stream()
                .map(ProofShotResponseItem::toDto)
                .toList();

        ProofShotResponseDto result = new ProofShotResponseDto();
        result.setTotal(proofShots.size());
        result.setProofShots(proofShotResponseItems);
        return result;
    }
}
