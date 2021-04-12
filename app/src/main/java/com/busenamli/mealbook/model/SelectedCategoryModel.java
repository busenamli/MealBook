package com.busenamli.mealbook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectedCategoryModel {

    @SerializedName("meals")
    private List<StrSelectedCategoryModel> selectedMeals = null;

    public List<StrSelectedCategoryModel> getSelectedCategoryMeals(){
        return selectedMeals;
    }
}
