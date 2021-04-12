package com.busenamli.mealbook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateModel {

    @SerializedName("meals")
    private List<StrAreaModel> meals = null;

    public List<StrAreaModel> getMeals(){
        return meals;
    }
}
