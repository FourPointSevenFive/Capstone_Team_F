package com.hallyugo.hallyugo.favorite.repository;

import com.hallyugo.hallyugo.favorite.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
