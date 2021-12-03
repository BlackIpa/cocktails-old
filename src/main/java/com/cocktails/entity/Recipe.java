package com.cocktails.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="recipe")
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

    @Column(name = "user_created")
    private boolean userCreated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recipes")
    private Set<RecipeIngredient> recipeIngredients;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getGlassId() {
        return glassId;
    }

    public void setGlassId(Long glassId) {
        this.glassId = glassId;
    }

    public boolean isIceMixing() {
        return iceMixing;
    }

    public void setIceMixing(boolean iceMixing) {
        this.iceMixing = iceMixing;
    }

    public boolean isIceGlass() {
        return iceGlass;
    }

    public void setIceGlass(boolean iceGlass) {
        this.iceGlass = iceGlass;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
    public Recipe() {
    }
}
