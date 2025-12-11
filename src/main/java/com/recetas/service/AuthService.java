package com.recetas.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recetas.dto.AuthRequest;
import com.recetas.dto.AuthResponse;
import com.recetas.dto.RegisterRequest;
import com.recetas.model.RefreshToken;
import com.recetas.model.User;
import com.recetas.repository.RefreshTokenRepository;
import com.recetas.repository.UserRepository;
import com.recetas.security.JwtUtil;

// Servicio de autenticación: gestiono registro, login, refresh tokens y cambio de contraseña
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Value("${jwt.refresh.exp-ms:2592000000}")
    private long refreshTokenDurationMs;

    @Value("${jwt.access.exp-ms:2592000000}")
    private long accessTokenDurationMs;

    public AuthService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // Registro un nuevo usuario y genero tokens automáticamente
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new com.recetas.exception.EmailAlreadyExistsException("El email ya está registrado");
        }
        if (req.getUsername() != null && userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new com.recetas.exception.UsernameAlreadyExistsException("El nombre de usuario ya está en uso");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        // Asignar rol por defecto (versión rápida)
        u.setRole(com.recetas.model.Role.ROLE_USER);
        userRepository.save(u);
        // Auto-login: genero tokens
        String access = jwtUtil.generateAccessToken(u.getEmail(), u.getId());
        RefreshToken rt = createRefreshToken(u);
        refreshTokenRepository.save(rt);
        AuthResponse resp = new AuthResponse(access, rt.getToken(), accessTokenDurationMs / 1000, u.getId());
        resp.setRole(u.getRole() != null ? u.getRole().name() : null);
        return resp;
    }

    // Autentico al usuario y genero tokens de acceso
    public AuthResponse login(AuthRequest req) {
        // Verificar si el email existe para distinguir error de email inválido vs contraseña inválida
        var userOpt = userRepository.findByEmail(req.getEmail());
        if (userOpt.isEmpty()) {
            throw new org.springframework.security.core.userdetails.UsernameNotFoundException("Email no registrado");
        }
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        } catch (org.springframework.security.authentication.BadCredentialsException ex) {
            throw new com.recetas.exception.InvalidPasswordException("Contraseña inválida");
        }
        User u = userOpt.get();
        String access = jwtUtil.generateAccessToken(req.getEmail(), u.getId());
        RefreshToken rt = createRefreshToken(u);
        refreshTokenRepository.save(rt);
        AuthResponse resp = new AuthResponse(access, rt.getToken(), accessTokenDurationMs / 1000, u.getId());
        resp.setRole(u.getRole() != null ? u.getRole().name() : null);
        return resp;
    }

    // Refresco el access token usando el refresh token válido
    public AuthResponse refresh(String refreshToken) {
        Optional<RefreshToken> r = refreshTokenRepository.findByToken(refreshToken);
        if (r.isEmpty() || r.get().isRevoked() || r.get().getExpiryDate().isBefore(Instant.now()))
            throw new RuntimeException("Refresh token inválido");
        RefreshToken existing = r.get();
        User user = existing.getUser();
        // Roto el refresh token: revoco el existente y emito uno nuevo
        existing.setRevoked(true);
        refreshTokenRepository.save(existing);
        RefreshToken newRt = createRefreshToken(user);
        refreshTokenRepository.save(newRt);
        String access = jwtUtil.generateAccessToken(user.getEmail(), user.getId());
        AuthResponse resp = new AuthResponse(access, newRt.getToken(), accessTokenDurationMs / 1000, user.getId());
        resp.setRole(user.getRole() != null ? user.getRole().name() : null);
        return resp;
    }

    // Revoco el refresh token para cerrar sesión
    public void logout(String refreshToken) {
        Optional<RefreshToken> r = refreshTokenRepository.findByToken(refreshToken);
        r.ifPresent(rt -> { rt.setRevoked(true); refreshTokenRepository.save(rt);} );
    }

    // Creo un nuevo refresh token para el usuario
    private RefreshToken createRefreshToken(User user) {
        RefreshToken rt = new RefreshToken();
        rt.setToken(UUID.randomUUID().toString());
        rt.setUser(user);
        rt.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        rt.setRevoked(false);
        return rt;
    }

    // Cambio la contraseña del usuario verificando la contraseña actual
    public void changePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isEmpty()) {
                throw new org.springframework.security.core.userdetails.UsernameNotFoundException("Email no registrado");
            }
            User user = userOpt.get();
            // Verifico que las nuevas contraseñas estén presentes
            if (newPassword == null || confirmPassword == null) {
                throw new com.recetas.exception.PasswordConfirmationMismatchException();
            }
            // Validación explícita de longitud para la nueva contraseña (coherente con registro)
            if (newPassword.length() < 8 || newPassword.length() > 128) {
                throw new com.recetas.exception.PasswordValidationException("size must be between 8 and 128");
            }
            // Verifico que las nuevas contraseñas coincidan
            if (!newPassword.equals(confirmPassword)) {
                throw new com.recetas.exception.PasswordConfirmationMismatchException();
            }
            // Verifico la contraseña actual
            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                throw new com.recetas.exception.OldPasswordMismatchException();
            }
            // Actualizo la contraseña
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
    }
}
