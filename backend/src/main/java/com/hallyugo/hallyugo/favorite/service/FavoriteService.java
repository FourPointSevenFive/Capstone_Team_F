package com.hallyugo.hallyugo.favorite.service;

import com.hallyugo.hallyugo.common.exception.EntityNotFoundException;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.favorite.domain.Favorite;
import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseDto;
import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseItem;
import com.hallyugo.hallyugo.favorite.repository.FavoriteRepository;
import com.hallyugo.hallyugo.image.domain.Image;
import com.hallyugo.hallyugo.image.repository.ImageRepository;
import com.hallyugo.hallyugo.location.domain.Location;
import com.hallyugo.hallyugo.location.repository.LocationRepository;
import com.hallyugo.hallyugo.user.domain.User;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final LocationRepository locationRepository;
    private final ImageRepository imageRepository;

    public FavoriteResponseDto getFavoritesByUser(User user, int limit) {
        List<Favorite> favorites = favoriteRepository.findByUserId(user.getId());

        List<Favorite> limitedFavorites = favorites.size() > limit ? favorites.subList(0, limit) : favorites;

        List<FavoriteResponseItem> favoriteResponseItems = limitedFavorites.stream()
                .map(this::createFavoriteResponseItem)
                .toList();

        FavoriteResponseDto result = new FavoriteResponseDto();
        result.setTotal(favorites.size());
        result.setFavorites(favoriteResponseItems);
        return result;
    }

    private FavoriteResponseItem createFavoriteResponseItem(Favorite favorite) {
        FavoriteResponseItem item = new FavoriteResponseItem();
        item.setId(favorite.getId());
        item.setLocationId(favorite.getLocation().getId());
        item.setCreatedAt(favorite.getCreatedAt());

        Location location = locationRepository.findById(favorite.getLocation().getId())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.ENTITY_NOT_FOUND));
        item.setTitle(location.getTitle());
        item.setDescription(location.getDescription());

        // ImageRepository를 사용하여 Location ID로 이미지를 조회
        List<Image> images = imageRepository.findByLocationId(location.getId());

        if (!images.isEmpty()) {
            // 첫 번째 이미지를 사용
            Image image = images.get(0);
            item.setImage(image.getImageUrl()); // Image 테이블의 이미지 URL을 설정
        } else {
            // 이미지가 없을 경우 기본값 또는 null 처리
            item.setImage(null);
        }
        return item;
    }

    @Transactional
    public void increaseFavoriteCountAndSave(User user, Long locationId) {
        // 전달된 locationId를 이용해 Location 객체 조회
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.ENTITY_NOT_FOUND));

        if (!favoriteRepository.existsByUserIdAndLocationId(user.getId(), locationId)) {
            // 해당 객체의 favoriteCount 1 증가
            location.increaseFavoriteCount();

            // favoriteCount가 갱신된 Location 객체 저장
            locationRepository.save(location);

            // Favorite 객체 생성 후 저장
            Favorite favorite = new Favorite(user, location);
            favoriteRepository.save(favorite);
        }
    }

    @Transactional
    public void decreaseFavoriteCountAndDelete(User user, Long locationId) {
        // 전달된 locationId를 이용해 Location 객체 조회
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.ENTITY_NOT_FOUND));

        if (favoriteRepository.existsByUserIdAndLocationId(user.getId(), locationId)) {
            // 해당 객체의 favoriteCount 1 감소
            location.decreaseFavoriteCount();

            // favoriteCount가 갱신된 Location 객체 저장
            locationRepository.save(location);

            // Favorite 객체 삭제
            favoriteRepository.deleteByUserIdAndLocationId(user.getId(), locationId);
        }
    }
}