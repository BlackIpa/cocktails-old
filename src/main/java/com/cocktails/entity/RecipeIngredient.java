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

//    @Column(name = "recipe_id")
//    private Long recipeId;
    @ManyToOne
    @JoinColumn(name="recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "amount")
    private Double amount;
}
