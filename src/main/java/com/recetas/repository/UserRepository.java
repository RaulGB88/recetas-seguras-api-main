package com.recetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.User;

// Proveo acceso a datos de usuarios y busco usuarios por email
public interface UserRepository extends JpaRepository<User, Integer> {
    // Busco usuario por email
    Optional<User> findByEmail(String email);
    // Busco usuario por username (para evitar usernames duplicados en registro)
    Optional<User> findByUsername(String username);
}
