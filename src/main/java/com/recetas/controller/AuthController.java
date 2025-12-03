package com.recetas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.dto.AuthRequest;
import com.recetas.dto.AuthResponse;
import com.recetas.dto.RegisterRequest;
import com.recetas.model.User;
import com.recetas.repository.UserRepository;
import com.recetas.service.AuthService;

import jakarta.validation.Valid;

// Controlador de autenticación: manejo registro, login, refresh token, logout y cambio de contraseña
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    // Registro de nuevo usuario y auto-login
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        AuthResponse res = authService.register(req);
        return ResponseEntity.status(201).body(res);
    }

    // Login con email y contraseña
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        AuthResponse res = authService.login(req);
        return ResponseEntity.ok(res);
    }

    // Refresco del access token usando el refresh token
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody java.util.Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        AuthResponse res = authService.refresh(refreshToken);
        return ResponseEntity.ok(res);
    }

    // Logout: revoco el refresh token
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody java.util.Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        authService.logout(refreshToken);
        return ResponseEntity.ok().build();
    }

    // Obtengo información del usuario autenticado
    @GetMapping("/me")
    public ResponseEntity<?> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            return ResponseEntity.status(401).build();
        }
        String email = auth.getName();
        java.util.Optional<User> u = userRepository.findByEmail(email);
        return u.map(user -> ResponseEntity.ok(java.util.Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "username", user.getUsername()
        ))).orElseGet(() -> ResponseEntity.status(404).build());
    }

    // Cambio la contraseña del usuario autenticado. Body: { "oldPassword": "...", "newPassword": "..." }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody java.util.Map<String, String> body) {
        if (userDetails == null || userDetails.getUsername() == null) {
            return ResponseEntity.status(401).body("No autenticado");
        }
        String email = userDetails.getUsername();
        String oldPassword = body.get("oldPassword");
        String newPassword = body.get("newPassword");
        if (oldPassword == null || newPassword == null) {
            return ResponseEntity.badRequest().body("Faltan campos");
        }
        boolean ok = authService.changePassword(email, oldPassword, newPassword);
        if (ok) {
            return ResponseEntity.ok().body("Contraseña actualizada");
        } else {
            return ResponseEntity.status(400).body("Contraseña actual incorrecta o error");
        }
    }
}
