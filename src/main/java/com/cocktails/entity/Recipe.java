package com.cocktails.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="recipe")
@Getter
@Setter
@ToString
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "recipe")
    private String name;

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "photo")
    private String photo;

    @Column(name = "glass_id")
    private Long glassId;

//    @OneToOne
//    @JoinColumn(name="glass_id", nullable = false)
//    private Glass glass;

    @Column(name = "ice_mixing")
    private boolean iceMixing;

    @Column(name = "ice_glass")
    private boolean iceGlass;

    @OneToMany(mappedBy = "recipes")
    private Set<RecipeIngredient> recipeIngredients;

    public Recipe() {

    }
}
