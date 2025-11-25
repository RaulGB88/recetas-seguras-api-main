package com.recetas.model;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "conditions")
public class Condition {
        @jakarta.persistence.PrePersist
        protected void onCreate() {
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }

        @jakarta.persistence.PreUpdate
        protected void onUpdate() {
            this.updatedAt = LocalDateTime.now();
        }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type", nullable = false)
    private ConditionType conditionType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "conditions")
    private Set<User> users;

    @ManyToMany
    @JoinTable(
        name = "condition_foods",
        joinColumns = @JoinColumn(name = "condition_id"),
        inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> foods;

    @ManyToMany
    @JoinTable(
        name = "condition_recipes",
        joinColumns = @JoinColumn(name = "condition_id"),
        inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<Recipe> recipes;

    // getters and setters

    public enum ConditionType {
        DISEASE, INTOLERANCE, ALLERGY
    }
}
