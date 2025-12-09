package com.recetas.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// Manejador global de excepciones: capturo errores de autenticación y generales del sistema
@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo excepción de usuario no encontrado
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleUsernameNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "error", "USER_NOT_FOUND",
                        "message", ex.getMessage()
                ));
    }

    // Manejo errores de runtime genéricos
        @ExceptionHandler(RuntimeException.class)
        @ResponseBody
        public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of(
                "error", "RUNTIME_ERROR",
                "message", ex.getMessage()
            ));
        }

    // Manejo excepciones genéricas no capturadas por otros handlers
        @ExceptionHandler(Exception.class)
        @ResponseBody
        public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of(
                "error", "INTERNAL_ERROR",
                "message", ex.getMessage()
            ));
        }
}
