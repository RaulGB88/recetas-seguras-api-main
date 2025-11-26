package com.recetas.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.RefreshToken;
import com.recetas.model.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
    List<RefreshToken> findByExpiryDateBefore(Instant time);
    void deleteByExpiryDateBefore(Instant time);
}
