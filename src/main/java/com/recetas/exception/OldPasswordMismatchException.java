package com.recetas.exception;

public class OldPasswordMismatchException extends RuntimeException {
    public OldPasswordMismatchException() { super("La contraseña actual no coincide"); }
    public OldPasswordMismatchException(String message) { super(message); }
}
