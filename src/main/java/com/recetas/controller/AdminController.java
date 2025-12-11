package com.recetas.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.repository.ConditionRepository;
import com.recetas.repository.FoodRepository;
import com.recetas.repository.RecipeRepository;
import com.recetas.repository.UserRepository;

// Administro endpoints administrativos (rápido)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final FoodRepository foodRepository;
    private final ConditionRepository conditionRepository;

    public AdminController(UserRepository userRepository, RecipeRepository recipeRepository, FoodRepository foodRepository, ConditionRepository conditionRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.foodRepository = foodRepository;
        this.conditionRepository = conditionRepository;
    }

    @GetMapping("/stats")
    // Protejo adicionalmente con SecurityConfig; @PreAuthorize es redundante pero lo dejo explícito
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Long>> stats() {
        long users = userRepository.count();
        long recipes = recipeRepository.count();
        long foods = foodRepository.count();
        long conditions = conditionRepository.count();
        Map<String, Long> body = Map.of(
            "users", users,
            "recipes", recipes,
            "foods", foods,
            "conditions", conditions
        );
        return ResponseEntity.ok(body);
    }
}
