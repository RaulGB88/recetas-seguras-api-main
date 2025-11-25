package com.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recetas.model.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
