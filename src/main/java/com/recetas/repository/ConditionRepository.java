package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.Condition;

// Proveo acceso a datos de condiciones médicas
public interface ConditionRepository extends JpaRepository<Condition, Integer> {
}
