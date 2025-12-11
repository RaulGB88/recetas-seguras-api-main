package com.recetas.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recetas.model.User;
import com.recetas.repository.UserRepository;

// Servicio de Spring Security: cargo detalles del usuario para autenticación
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Cargo el usuario por email para la autenticación de Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(username);
        if (userOpt.isEmpty()) {
            // Lanzo excepción si no encuentro el usuario
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        User user = userOpt.get();
        String roleName = user.getRole() != null ? user.getRole().name() : "ROLE_USER";
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singleton(authority));
    }
}
