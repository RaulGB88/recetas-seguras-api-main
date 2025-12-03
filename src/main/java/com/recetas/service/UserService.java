package com.recetas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.recetas.dto.ConditionDto;
import com.recetas.dto.FoodDto;
import com.recetas.dto.RecipeDto;
import com.recetas.model.Condition;
import com.recetas.model.RecipeFood;
import com.recetas.model.User;
import com.recetas.repository.ConditionRepository;
import com.recetas.repository.FoodRepository;
import com.recetas.repository.RecipeRepository;
import com.recetas.repository.UserRepository;

// Servicio de usuarios: lógica de negocio para gestionar usuarios, condiciones y filtros de seguridad alimentaria
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ConditionRepository conditionRepository;
    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    public UserService(UserRepository userRepository, ConditionRepository conditionRepository, FoodRepository foodRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.conditionRepository = conditionRepository;
        this.foodRepository = foodRepository;
        this.recipeRepository = recipeRepository;
    }
    // Obtengo recetas seguras: filtro las que no tengan ingredientes prohibidos por las condiciones del usuario
    public List<RecipeDto> getSafeRecipes(@org.springframework.lang.NonNull Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var userConditions = user.getConditions();
        // Reúno todos los alimentos prohibidos por las condiciones del usuario
        var forbiddenFoods = userConditions.stream()
            .filter(cond -> cond.getFoods() != null)
            .flatMap(cond -> cond.getFoods().stream())
            .collect(Collectors.toSet());
        // Filtro recetas que sean seguras (ningún ingrediente prohibido)
        return recipeRepository.findAll().stream()
            .filter(recipe -> {
                if (recipe.getRecipeFoods() != null) {
                    for (RecipeFood rf : recipe.getRecipeFoods()) {
                        if (forbiddenFoods.contains(rf.getFood())) {
                            return false;
                        }
                    }
                }
                return true;
            })
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

    // Obtengo todos los usuarios del repositorio
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Asigno condiciones médicas a un usuario específico
    public List<ConditionDto> setUserConditions(@org.springframework.lang.NonNull Integer userId, List<Integer> conditionIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Condition> conditions = conditionRepository.findAllById(() -> conditionIds.iterator());
        user.setConditions(conditions.stream().collect(Collectors.toSet()));
        userRepository.save(user);
        return conditions.stream().map(cond -> {
            ConditionDto dto = new ConditionDto();
            dto.id = cond.getId();
            dto.name = cond.getName();
            dto.description = cond.getDescription();
            dto.conditionType = cond.getConditionType().name();
            return dto;
        }).collect(Collectors.toList());
    }

    // Obtengo comidas permitidas: filtro las que NO estén prohibidas por ninguna condición del usuario
    public List<FoodDto> getSafeFoods(@org.springframework.lang.NonNull Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // Reúno todos los alimentos prohibidos por las condiciones del usuario
        var forbiddenFoods = user.getConditions().stream()
            .filter(cond -> cond.getFoods() != null)
            .flatMap(cond -> cond.getFoods().stream())
            .collect(Collectors.toSet());
        // Filtrar todos los alimentos que NO están en forbiddenFoods
        return foodRepository.findAll().stream()
            .filter(food -> !forbiddenFoods.contains(food))
            .map(food -> {
                FoodDto dto = new FoodDto();
                dto.id = food.getId();
                dto.name = food.getName();
                dto.category = food.getCategory() != null ? food.getCategory().name() : null;
                return dto;
            })
            .collect(Collectors.toList());
    }

    // Obtengo las condiciones médicas asociadas a un usuario
    public List<ConditionDto> getUserConditions(@org.springframework.lang.NonNull Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getConditions().stream()
            .map(cond -> {
                ConditionDto dto = new ConditionDto();
                dto.id = cond.getId();
                dto.name = cond.getName();
                dto.description = cond.getDescription();
                dto.conditionType = cond.getConditionType().name();
                return dto;
            })
            .collect(Collectors.toList());
    }
}
