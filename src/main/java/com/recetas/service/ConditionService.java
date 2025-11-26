package com.recetas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recetas.model.Condition;
import com.recetas.repository.ConditionRepository;

@Service
public class ConditionService {
    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }
}
