package com.recetas.exception;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;

public final class ErrorResponseUtil {
    private ErrorResponseUtil() {}

    public static Map<String, Object> buildError(HttpStatus status, String code, String message) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "code", code,
                "message", message
        );
    }
}
