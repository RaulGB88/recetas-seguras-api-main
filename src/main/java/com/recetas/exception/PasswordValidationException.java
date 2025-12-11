package com.recetas.exception;

public class PasswordValidationException extends RuntimeException {
    public PasswordValidationException() { super("Password validation failed"); }
    public PasswordValidationException(String message) { super(message); }
}
