package com.recetas.exception;

public class PasswordConfirmationMismatchException extends RuntimeException {
    public PasswordConfirmationMismatchException() { super("Las nuevas contraseñas no coinciden"); }
    public PasswordConfirmationMismatchException(String message) { super(message); }
}
