package com.recetas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.recetas.dto.RecipeDto;
import com.recetas.repository.RecipeRepository;

// Servicio de recetas: lógica de negocio para consultar recetas con sus ingredientes
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // Obtengo todas las recetas con sus ingredientes mapeados a DTOs
    public List<RecipeDto> getAllRecipes() {
        return recipeRepository.findAll().stream()
            .map(recipe -> {
                RecipeDto dto = new RecipeDto();
                dto.id = recipe.getId();
                dto.title = recipe.getTitle();
                dto.description = recipe.getDescription();
                dto.steps = recipe.getSteps();
                dto.ingredients = recipe.getRecipeFoods() != null 
                    ? recipe.getRecipeFoods().stream()
                        .map(rf -> {
                            RecipeDto.RecipeIngredientDto ingredient = new RecipeDto.RecipeIngredientDto();
                            ingredient.foodId = rf.getFood().getId();
                            ingredient.foodName = rf.getFood().getName();
                            ingredient.quantity = rf.getQuantity();
                            return ingredient;
                        })
                        .collect(Collectors.toList())
                    : List.of();
                return dto;
            })
            .collect(Collectors.toList());
    }
}
