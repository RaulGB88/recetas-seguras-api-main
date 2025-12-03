package com.recetas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recetas.model.Food;
import com.recetas.repository.FoodRepository;

// Servicio de alimentos: lógica de negocio para consultar alimentos
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // Obtengo todos los alimentos del repositorio
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
