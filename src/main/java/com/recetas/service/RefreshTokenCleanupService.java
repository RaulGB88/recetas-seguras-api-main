package com.recetas.service;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.recetas.repository.RefreshTokenRepository;

@Service
public class RefreshTokenCleanupService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final Logger logger = LoggerFactory.getLogger(RefreshTokenCleanupService.class);

    public RefreshTokenCleanupService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // runs every hour by default; configurable via app.token.cleanup-ms
    @Scheduled(fixedDelayString = "${app.token.cleanup-ms:3600000}")
    public void cleanupExpiredRefreshTokens() {
        Instant now = Instant.now();
        try {
            int before = refreshTokenRepository.findAll().size();
            refreshTokenRepository.deleteByExpiryDateBefore(now);
            int after = refreshTokenRepository.findAll().size();
            logger.info("RefreshToken cleanup executed. Removed {} expired tokens.", Math.max(0, before - after));
        } catch (Exception ex) {
            logger.error("Error while cleaning up refresh tokens", ex);
        }
    }
}
