package com.recetas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.dto.ConditionDto;
import com.recetas.dto.FoodDto;
import com.recetas.dto.RecipeDto;
import com.recetas.dto.UserConditionRequest;
import com.recetas.model.User;
import com.recetas.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // POST /api/users/{id}/conditions
    @PostMapping("/{id}/conditions")
    public List<ConditionDto> setUserConditions(@PathVariable @org.springframework.lang.NonNull Integer id, @RequestBody UserConditionRequest request) {
        return userService.setUserConditions(id, request.conditionIds);
    }

    // GET /api/users/{id}/safe-foods
    @GetMapping("/{id}/safe-foods")
    public List<FoodDto> getSafeFoods(@PathVariable @org.springframework.lang.NonNull Integer id) {
        return userService.getSafeFoods(id);
    }

    // GET /api/users/{id}/safe-recipes
    @GetMapping("/{id}/safe-recipes")
    public List<RecipeDto> getSafeRecipes(@PathVariable @org.springframework.lang.NonNull Integer id) {
        return userService.getSafeRecipes(id);
    }
}
