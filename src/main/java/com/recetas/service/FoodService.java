package com.recetas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recetas.model.Food;
import com.recetas.repository.FoodRepository;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
