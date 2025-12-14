package com.recetas.exception;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.ConstraintViolationException;

// Capturo errores de autenticación y errores generales del sistema
@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo excepción de usuario no encontrado
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleUsernameNotFound(UsernameNotFoundException ex) {
        // Cuando el email no está registrado, devuelvo un código específico
        // para facilitar el manejo por parte del frontend.
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildError(HttpStatus.UNAUTHORIZED, "USER_NOT_FOUND", ex.getMessage()));
    }

    // Manejo errores de runtime genéricos
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildError(HttpStatus.BAD_REQUEST, "RUNTIME_ERROR", ex.getMessage()));
    }

    // Manejo validación de DTOs con @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleMethodArgNotValid(MethodArgumentNotValidException ex) {
        var fieldErrorObjs = ex.getBindingResult().getFieldErrors();
        var fieldErrors = fieldErrorObjs.stream()
                .map(fe -> Map.of("field", fe.getField(), "message", fe.getDefaultMessage()))
                .toList();
        // Prioritizo errores de validación del campo `email` por encima de `password`.
        var emailFieldError = fieldErrorObjs.stream()
                .filter(fe -> fe.getField() != null && fe.getField().toLowerCase().contains("email"))
                .findFirst();
        var passwordFieldError = fieldErrorObjs.stream()
                .filter(fe -> fe.getField() != null && fe.getField().toLowerCase().contains("password"))
                .findFirst();
        // Si existe un error en el campo `email`, lo prefiero. Solo si no hay error de email
        // lanzo PasswordValidationException.
        if (emailFieldError.isPresent()) {
            // Construyo la respuesta de validación normal más abajo (sin escalado a password)
        } else if (passwordFieldError.isPresent()) {
            // Escalo a PasswordValidationException solo cuando no exista error de email
            throw new com.recetas.exception.PasswordValidationException(passwordFieldError.get().getDefaultMessage());
        }

        String topMessage = fieldErrors.isEmpty() ? "Validation failed" : (String) fieldErrors.get(0).get("message");
        var body = Map.of(
                "timestamp", java.time.Instant.now().toString(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "code", "VALIDATION_FAILED",
                "message", topMessage,
                "errors", fieldErrors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // Manejo ConstraintViolation (ej. parámetros path/query)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex) {
        var violations = ex.getConstraintViolations().stream()
                .map(cv -> Map.of("path", cv.getPropertyPath().toString(), "message", cv.getMessage()))
                .toList();
        // Detecto violaciones sobre `email` o `password` y priorizo `email`.
        var emailViolation = ex.getConstraintViolations().stream()
                .filter(cv -> cv.getPropertyPath() != null && cv.getPropertyPath().toString().toLowerCase().contains("email"))
                .findFirst();
        var pw = ex.getConstraintViolations().stream()
                .filter(cv -> cv.getPropertyPath() != null && cv.getPropertyPath().toString().toLowerCase().contains("password"))
                .findFirst();
        // Si hay una violación sobre `email`, la prefiero. Solo lanzo PasswordValidationException
        // cuando no exista una violación de email.
        if (emailViolation.isPresent()) {
            // Construyo la respuesta estándar de CONSTRAINT_VIOLATION usando la lista `violations`
        } else if (pw.isPresent()) {
            throw new com.recetas.exception.PasswordValidationException(pw.get().getMessage());
        }
        String topMessage = violations.isEmpty() ? "Constraint violation" : (String) violations.get(0).get("message");
        var body = Map.of(
                "timestamp", java.time.Instant.now().toString(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "code", "CONSTRAINT_VIOLATION",
                "message", topMessage,
                "errors", violations
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // Manejo parseo JSON inválido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildError(HttpStatus.BAD_REQUEST, "MALFORMED_JSON", ex.getMessage()));
    }

    // Manejo acceso denegado
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(buildError(HttpStatus.FORBIDDEN, "ACCESS_DENIED", ex.getMessage()));
    }

    // Manejo excepciones de autenticación (fallback)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildError(HttpStatus.UNAUTHORIZED, "AUTH_ERROR", ex.getMessage()));
    }

    // Manejo validación específica de password
    @ExceptionHandler(PasswordValidationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handlePasswordValidation(PasswordValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildError(HttpStatus.BAD_REQUEST, "PASSWORD_ERROR", ex.getMessage()));
    }

    // Manejo contraseña inválida
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleInvalidPassword(InvalidPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildError(HttpStatus.UNAUTHORIZED, "INVALID_PASSWORD", ex.getMessage()));
    }

    // Cuando la contraseña actual no coincide al cambiar contraseña, devuelvo 400
    @ExceptionHandler(OldPasswordMismatchException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleOldPasswordMismatch(OldPasswordMismatchException ex) {
        // Devolver 400 Bad Request en lugar de 401 para evitar que clientes
        // traten esto como un problema de autenticación y potencialmente reintenten
        // la petición generando loops.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildError(HttpStatus.BAD_REQUEST, "OLD_PASSWORD_MISMATCH", ex.getMessage()));
    }

    // Cuando las nuevas contraseñas no coinciden, devuelvo error 400
    @ExceptionHandler(PasswordConfirmationMismatchException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handlePasswordConfirmationMismatch(PasswordConfirmationMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildError(HttpStatus.BAD_REQUEST, "PASSWORD_CONFIRMATION_MISMATCH", ex.getMessage()));
    }

    // Cuando el usuario no está autenticado para una operación, devuelvo 401
    @ExceptionHandler(AuthenticationRequiredException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleAuthenticationRequired(AuthenticationRequiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildError(HttpStatus.UNAUTHORIZED, "NOT_AUTHENTICATED", ex.getMessage()));
    }

    // Cuando faltan campos obligatorios en la petición, devuelvo 400
    @ExceptionHandler(MissingFieldsException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleMissingFields(MissingFieldsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildError(HttpStatus.BAD_REQUEST, "MISSING_FIELDS", ex.getMessage()));
    }

    // Cuando el email ya está registrado, devuelvo 409
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleEmailExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(buildError(HttpStatus.CONFLICT, "EMAIL_ALREADY_EXISTS", ex.getMessage()));
    }

    // Cuando el username ya está registrado, devuelvo 409
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleUsernameExists(UsernameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(buildError(HttpStatus.CONFLICT, "USERNAME_ALREADY_EXISTS", ex.getMessage()));
    }

    // Manejo excepciones genéricas no capturadas por otros handlers
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildError(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", ex.getMessage()));
    }

    // Helper para construir el envelope de error consistente
    private Map<String, Object> buildError(org.springframework.http.HttpStatus status, String code, String message) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "code", code,
                "message", message
        );
    }
}
