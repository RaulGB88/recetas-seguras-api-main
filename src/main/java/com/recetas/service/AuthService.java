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

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Value("${jwt.refresh.exp-ms:2592000000}")
    private long refreshTokenDurationMs;

    @Value("${jwt.access.exp-ms:3600000}")
    private long accessTokenDurationMs;

    public AuthService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(u);
        // auto-login: generate tokens
        String access = jwtUtil.generateAccessToken(u.getEmail(), u.getId());
        RefreshToken rt = createRefreshToken(u);
        refreshTokenRepository.save(rt);
        return new AuthResponse(access, rt.getToken(), accessTokenDurationMs / 1000, u.getId());
    }

    public AuthResponse login(AuthRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        User u = userRepository.findByEmail(req.getEmail()).get();
        String access = jwtUtil.generateAccessToken(req.getEmail(), u.getId());
        RefreshToken rt = createRefreshToken(u);
        refreshTokenRepository.save(rt);
        return new AuthResponse(access, rt.getToken(), accessTokenDurationMs / 1000, u.getId());
    }

    public AuthResponse refresh(String refreshToken) {
        Optional<RefreshToken> r = refreshTokenRepository.findByToken(refreshToken);
        if (r.isEmpty() || r.get().isRevoked() || r.get().getExpiryDate().isBefore(Instant.now()))
            throw new RuntimeException("Refresh token inválido");
        RefreshToken existing = r.get();
        User user = existing.getUser();
        // rotate refresh token: revoke existing and issue a new one
        existing.setRevoked(true);
        refreshTokenRepository.save(existing);
        RefreshToken newRt = createRefreshToken(user);
        refreshTokenRepository.save(newRt);
        String access = jwtUtil.generateAccessToken(user.getEmail(), user.getId());
        return new AuthResponse(access, newRt.getToken(), accessTokenDurationMs / 1000, user.getId());
    }

    public void logout(String refreshToken) {
        Optional<RefreshToken> r = refreshTokenRepository.findByToken(refreshToken);
        r.ifPresent(rt -> { rt.setRevoked(true); refreshTokenRepository.save(rt);} );
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken rt = new RefreshToken();
        rt.setToken(UUID.randomUUID().toString());
        rt.setUser(user);
        rt.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        rt.setRevoked(false);
        return rt;
    }
}
