package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.Condition;

public interface ConditionRepository extends JpaRepository<Condition, Integer> {
}
