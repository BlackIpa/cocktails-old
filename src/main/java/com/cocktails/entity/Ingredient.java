package com.cocktails.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
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
    private String ingredient;

    @Column(name = "kind")
    private String kind;

    @OneToMany(mappedBy = "ingredients")
    private Set<RecipeIngredient> recipeIngredients;

//    @ManyToMany(fetch=FetchType.LAZY,
//                cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(name="recipe_ingredient",
//               joinColumns = @JoinColumn(name="ingredient_id"),
//               inverseJoinColumns = @JoinColumn(name="recipe_id"))
//    private List<Recipe> recipes;
}
