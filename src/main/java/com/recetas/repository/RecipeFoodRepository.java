package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.RecipeFood;

// Repositorio de ingredientes de recetas: acceso a la tabla intermedia receta-alimento
public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Integer> {
}
