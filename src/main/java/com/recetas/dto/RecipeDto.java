package com.recetas.dto;

import java.util.List;

// DTO para transferir datos de recetas con sus ingredientes
public class RecipeDto {
    public Integer id;
    public String title;
    public String description;
    public String steps;
    public List<RecipeIngredientDto> ingredients;
    
    // DTO anidado para ingredientes de una receta
    public static class RecipeIngredientDto {
        public Integer foodId;
        public String foodName;
        public String quantity;
    }
}
