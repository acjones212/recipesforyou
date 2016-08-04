package com.austincjones.rfy;

/**
 * Created by PRABHU on 11/12/2015.
 */
public class DatabaseModel {
    private String name;
    private String ingredients;
    private String directions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
