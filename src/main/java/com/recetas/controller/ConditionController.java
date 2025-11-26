package com.recetas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.model.Condition;
import com.recetas.service.ConditionService;

@RestController
@RequestMapping("/api/conditions")
public class ConditionController {
    private final ConditionService conditionService;

    public ConditionController(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @GetMapping
    public List<Condition> getAllConditions() {
        return conditionService.getAllConditions();
    }
}
