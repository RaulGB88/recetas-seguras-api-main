package com.recetas.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class StartupValidator {
    private final Logger logger = LoggerFactory.getLogger(StartupValidator.class);

    @Value("${jwt.secret:}")
    private String jwtSecret;

    @PostConstruct
    public void validate() {
        if (jwtSecret == null || jwtSecret.isBlank()) {
            logger.error("Missing required environment variable: JWT_SECRET. The application will not start without it.");
            throw new IllegalStateException("Environment variable JWT_SECRET must be set (Base64-encoded key)");
        }
        logger.info("JWT secret detected via environment variable.");
    }
}
