package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.Food;

// Repositorio de alimentos: acceso a datos de alimentos
public interface FoodRepository extends JpaRepository<Food, Integer> {
}
