package com.recetas.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.RefreshToken;
import com.recetas.model.User;

// Repositorio de refresh tokens: acceso y limpieza de tokens de autenticación
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    // Busco refresh token por su valor
    Optional<RefreshToken> findByToken(String token);
    // Elimino todos los tokens de un usuario específico
    void deleteByUser(User user);
    // Busco tokens que expiraron antes de cierto momento
    List<RefreshToken> findByExpiryDateBefore(Instant time);
    // Elimino tokens que expiraron antes de cierto momento
    void deleteByExpiryDateBefore(Instant time);
}
