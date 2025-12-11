package com.recetas.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// Capturo y formateo errores de API en respuestas consistentes
@ControllerAdvice
public class ApiExceptionHandler {

    // Nota: no manejo RuntimeException genérico aquí; dejo que los handlers
    // específicos en `GlobalExceptionHandler` (p.ej. InvalidPasswordException)
    // se apliquen y devuelvan el campo `code` requerido por los tests.

    // Manejo errores de validación de datos de entrada
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(org.springframework.web.bind.MethodArgumentNotValidException ex, WebRequest req) {
        Map<String,Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        body.put("error", "Validation Failed");
        java.util.List<Map<String,String>> errors = new java.util.ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fe -> {
            errors.add(Map.of("field", fe.getField(), "message", fe.getDefaultMessage()));
        });
        // Detecto si hay un error sobre el campo password y expongo código específico
        boolean hasPasswordError = ex.getBindingResult().getFieldErrors().stream()
                .anyMatch(fe -> fe.getField() != null && fe.getField().toLowerCase().contains("password"));

        // Uso el primer mensaje de error de campo (si existe) como mensaje principal
        // y mantengo compatibilidad con lo que requieren los tests.
        String topMessage = errors.isEmpty() ? "Validation error" : errors.get(0).get("message");
        body.put("message", topMessage);
        // Uso la clave `errors` para compatibilidad con las verificaciones de tests (se requiere `errors` en lugar de `details`)
        body.put("errors", errors);
        body.put("path", req.getDescription(false));
        // Añado código específico cuando corresponda
        body.put("code", hasPasswordError ? "PASSWORD_ERROR" : "VALIDATION_FAILED");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }
}
