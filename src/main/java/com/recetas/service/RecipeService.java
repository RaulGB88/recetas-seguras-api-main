package com.recetas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recetas.model.Recipe;
import com.recetas.repository.RecipeRepository;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
