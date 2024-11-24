package com.hallyugo.hallyugo.proof.service;

import com.hallyugo.hallyugo.common.exception.EntityNotFoundException;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.common.exception.InvalidFileException;
import com.hallyugo.hallyugo.location.domain.Location;
import com.hallyugo.hallyugo.location.repository.LocationRepository;
import com.hallyugo.hallyugo.proof.domain.ProofShot;
import com.hallyugo.hallyugo.proof.domain.request.ProofShotRequestDto;
import com.hallyugo.hallyugo.proof.domain.response.ProofShotResponseDto;
import com.hallyugo.hallyugo.proof.domain.response.ProofShotResponseItem;
import com.hallyugo.hallyugo.proof.domain.response.ProofShotUploadResponseDto;
import com.hallyugo.hallyugo.proof.repository.ProofShotRepository;
import com.hallyugo.hallyugo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProofShotService {

    private final ProofShotRepository proofShotRepository;
    private final LocationRepository locationRepository;
    private final S3Uploader s3Uploader;

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

    public ProofShotUploadResponseDto saveProofShot(User user, ProofShotRequestDto proofShotRequestDto, MultipartFile image) throws IOException {

        if(!Objects.requireNonNull(image.getContentType()).startsWith("image")) {
            throw new InvalidFileException(ExceptionCode.INVALID_FILE_TYPE);
        }

        String imageUrl = s3Uploader.uploadFile(image);

        Location location = locationRepository.findById(proofShotRequestDto.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.ENTITY_NOT_FOUND));

        ProofShot proofShot = new ProofShot(user, location, imageUrl, proofShotRequestDto.getDescription());

        ProofShot savedProofShot = proofShotRepository.save(proofShot);

        return ProofShotUploadResponseDto.toDto(savedProofShot);
    }

    public void deleteProofShot(User user, Long proofShotId) {
        ProofShot proofShot = proofShotRepository.findById(proofShotId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.ENTITY_NOT_FOUND));

        String imageUrl = proofShot.getImageUrl();
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        s3Uploader.deleteFile(fileName);
    }
}
