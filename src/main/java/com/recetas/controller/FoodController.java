package com.recetas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.model.Food;
import com.recetas.service.FoodService;

// Controlador de alimentos: manejo consultas de todos los alimentos disponibles
@RestController
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // Obtengo todos los alimentos
    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }
}
