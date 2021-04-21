package com.busenamli.mealbook.model;

public class IngredientsModel {
    String ingredients;
    String measure;

    public IngredientsModel(String ingredients, String measure) {
        this.ingredients = ingredients;
        this.measure = measure;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
