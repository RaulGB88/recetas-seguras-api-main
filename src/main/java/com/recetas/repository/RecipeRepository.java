package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
