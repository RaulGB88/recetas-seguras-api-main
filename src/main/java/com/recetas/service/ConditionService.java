package com.recetas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recetas.model.Condition;
import com.recetas.repository.ConditionRepository;

// Servicio de condiciones: lógica de negocio para consultar condiciones médicas
@Service
public class ConditionService {
    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    // Obtengo todas las condiciones del repositorio
    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }
}
