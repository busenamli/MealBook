package com.busenamli.mealbook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectedMealModel {

    @SerializedName("meals")
    private List<StrSelectedMealModel> selectedMeals = null;

    public List<StrSelectedMealModel> getSelectedMeal(){
        return selectedMeals;
    }
}
