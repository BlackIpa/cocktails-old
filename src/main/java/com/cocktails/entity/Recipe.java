package com.cocktails.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
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

//    @OneToOne
//    @JoinColumn(name="glass_id", nullable = false)
//    private Glass glass;

    @Column(name = "ice_mixing")
    private boolean iceMixing;

    @Column(name = "ice_glass")
    private boolean iceGlass;

    @OneToMany(mappedBy = "recipes")
    private Set<RecipeIngredient> recipeIngredients;

//    @ManyToMany(fetch=FetchType.LAZY,
//                cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(name="recipe_ingredient",
//               joinColumns = @JoinColumn(name="recipe_id"),
//               inverseJoinColumns = @JoinColumn(name="ingredient_id"))
//    private List<Ingredient> ingredients;
//
//    @ManyToMany(fetch=FetchType.LAZY,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(name="recipe_ingredient",
//            joinColumns = @JoinColumn(name="recipe_id"),
//            inverseJoinColumns = @JoinColumn(name="unit_id"))
//    private List<Unit> units;
}
