package com.recetas.exception;

public class AuthenticationRequiredException extends RuntimeException {
    public AuthenticationRequiredException() { super("No autenticado"); }
    public AuthenticationRequiredException(String message) { super(message); }
}
