package com.cocktails.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="recipe")
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "recipe")
    private String recipe;

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "photo")
    private String photo;

    @Column(name = "glass_id")
    private Long glassId;

    @Column(name = "ice_mixing")
    private boolean iceMixing;

    @Column(name = "ice_glass")
    private boolean iceGlass;

    @Column(name = "base_id")
    private Long baseId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<RecipeIngredient> recipeIngredients;
}
