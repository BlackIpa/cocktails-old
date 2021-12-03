package com.cocktails.controller.model;

import com.cocktails.entity.Ingredient;
import com.cocktails.entity.Unit;

import java.util.List;

public class RecipeDTO {

    private String name;
    private String instruction;
    private String photo;
    private Long glassId;
    private boolean iceMixing;
    private boolean iceGlass;
    private List<Ingredient> ingredients;
    private List<Double> amounts;
    private List<Unit> units;

    // getters and setters

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Double> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Double> amounts) {
        this.amounts = amounts;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public RecipeDTO() {
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "name='" + name + '\'' +
                ", instruction='" + instruction + '\'' +
                ", photo='" + photo + '\'' +
                ", glassId=" + glassId +
                ", iceMixing=" + iceMixing +
                ", iceGlass=" + iceGlass +
                ", ingredients=" + ingredients +
                ", amounts=" + amounts +
                ", units=" + units +
                '}';
    }
}
