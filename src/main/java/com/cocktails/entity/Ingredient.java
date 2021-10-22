package com.cocktails.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="ingredient")
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "ingredient")
    private String name;

    @Column(name = "kind")
    private String kind;

    @OneToMany(mappedBy = "ingredients")
    @JsonIgnore
    private Set<RecipeIngredient> recipeIngredients;
}
