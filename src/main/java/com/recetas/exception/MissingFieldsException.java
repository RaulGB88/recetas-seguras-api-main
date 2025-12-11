package com.recetas.exception;

public class MissingFieldsException extends RuntimeException {
    public MissingFieldsException() { super("Faltan campos"); }
    public MissingFieldsException(String message) { super(message); }
}
