package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.Recipe;

// Repositorio de recetas: acceso a datos de recetas
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
