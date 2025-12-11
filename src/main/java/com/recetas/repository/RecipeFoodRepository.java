package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.RecipeFood;

// Proveo acceso a la tabla intermedia receta-alimento (ingredientes de recetas)
public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Integer> {
}
