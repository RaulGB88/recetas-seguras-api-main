package com.recetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.User;

// Repositorio de usuarios: acceso a datos de usuarios con búsqueda por email
public interface UserRepository extends JpaRepository<User, Integer> {
    // Busco usuario por email
    Optional<User> findByEmail(String email);
}
