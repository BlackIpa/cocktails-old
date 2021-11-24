package com.cocktails.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "units")
    @JsonIgnore
    private Set<RecipeIngredient> recipeIngredients;
}
