package com.cocktails.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="recipe_ingredient")
@Getter
@Setter
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_ingredient_id")
    private Long recipeIngredientId;

    @ManyToOne
    @JoinColumn(name="recipe_id", nullable = false)
    private Recipe recipes;

    @ManyToOne
    @JoinColumn(name="ingredient_id", nullable = false)
    private Ingredient ingredients;

    @Column(name = "amount")
    private Double amount;

//    @ManyToOne
//    @JoinColumn(name="unit_id", nullable = false)
//    private Unit units;
//
//    @ManyToMany
//    @JoinColumn(name="ingredient_id", nullable = false)
//    private Ingredient ingredient;
//    @Column(name = "ingredient_id")
//    private Long ingredientId;
//
//    @Column(name = "unit_id")
//    private Long unitId;
}
