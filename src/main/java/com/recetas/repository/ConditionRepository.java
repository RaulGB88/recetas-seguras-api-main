package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.Condition;

// Repositorio de condiciones: acceso a datos de condiciones médicas
public interface ConditionRepository extends JpaRepository<Condition, Integer> {
}
