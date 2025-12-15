package com.recetas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Defino DTO para solicitud de autenticación (login)
public class AuthRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 128)
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "La contraseña debe contener letras y números.")
    /* @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe contener al menos una mayúscula, una minúscula y un número.") */
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!*()_\\-]).{8,}$",
            message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial (@#$%^&+=!*()_-).")
    private String password;

    public AuthRequest() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
