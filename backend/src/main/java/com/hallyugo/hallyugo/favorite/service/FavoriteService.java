package com.hallyugo.hallyugo.favorite.service;

import com.hallyugo.hallyugo.common.exception.EntityNotFoundException;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.content.domain.Content;
import com.hallyugo.hallyugo.content.repository.ContentRepository;
import com.hallyugo.hallyugo.favorite.domain.Favorite;
import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseDto;
import com.hallyugo.hallyugo.favorite.domain.response.FavoriteResponseItem;
import com.hallyugo.hallyugo.favorite.repository.FavoriteRepository;
import com.hallyugo.hallyugo.image.domain.Image;
import com.hallyugo.hallyugo.image.repository.ImageRepository;
import com.hallyugo.hallyugo.location.domain.Location;
import com.hallyugo.hallyugo.location.repository.LocationRepository;
import com.hallyugo.hallyugo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ContentRepository contentRepository;
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
        item.setEntityId(favorite.getEntityId());
        item.setEntityType(favorite.getEntityType());
        item.setCreatedAt(favorite.getCreatedAt());

        switch (favorite.getEntityType()) {
            case CONTENT -> {
                Content content = contentRepository.findById(favorite.getEntityId())
                        .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.ENTITY_NOT_FOUND));
                item.setTitle(content.getTitle());
                item.setDescription(content.getDescription());
                item.setImage(content.getContentImageUrl());
            }
            case LOCATION -> {
                Location location = locationRepository.findById(favorite.getEntityId())
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

            }
            default -> throw new EntityNotFoundException(ExceptionCode.UNSUPPORTED_ENTITY_TYPE);
        }
        return item;
    }
}