package com.cocktails.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="unit")
@Getter
@Setter
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "unit")
    private String unit;

//    @ManyToMany(fetch=FetchType.LAZY,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(name="recipe_ingredient",
//            joinColumns = @JoinColumn(name="unit_id"),
//            inverseJoinColumns = @JoinColumn(name="recipe_id"))
//    private List<Recipe> recipes;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "units")
//    private Set<RecipeIngredient> recipeIngredients;
}
