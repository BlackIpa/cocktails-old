package com.cocktails.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Recipe recipes;

    @ManyToOne
    @JoinColumn(name="ingredient_id", nullable = false)
    @JsonIgnore
    private Ingredient ingredients;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name="unit_id", nullable = false)
    private Unit units;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredients, Double amount, Unit units) {
        this.ingredients = ingredients;
        this.amount = amount;
        this.units = units;
    }

}
