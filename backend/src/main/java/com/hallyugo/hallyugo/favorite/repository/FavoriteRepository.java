package com.hallyugo.hallyugo.favorite.repository;

import com.hallyugo.hallyugo.favorite.domain.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);

    boolean existsByUserIdAndLocationId(Long userId, Long locationId);

    void deleteByUserIdAndLocationId(Long userId, Long locationId);

    long countByLocationId(Long locationId);
}
