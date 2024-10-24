package com.hallyugo.hallyugo.auth.repository;

import com.hallyugo.hallyugo.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
